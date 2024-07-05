package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.dto.ProductDTO;
import com.crm.organizecrm.exception.ProductNotFoundException;
import com.crm.organizecrm.mapper.DepartmentMapper;
import com.crm.organizecrm.mapper.ProductMapper;
import com.crm.organizecrm.model.Company;
import com.crm.organizecrm.model.Department;
import com.crm.organizecrm.model.Product;
import com.crm.organizecrm.repository.ProductRepository;
import com.crm.organizecrm.service.ProductService;
import com.crm.organizecrm.service.QRCodeService;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final QRCodeService qrCodeService;
    private final DepartmentServiceImpl departmentService;
    @Value("${base.url}")
    private String baseUrl;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toEntity(productDTO);
        Department department = DepartmentMapper.toEntity(departmentService.getDepartmentById(productDTO.getDepartmentId()));
        product.setDepartment(department);
        Product savedProduct = productRepository.save(product);
        savedProduct.setQrCode(generateQRCodeForProduct(savedProduct));
        return ProductMapper.toDTO(productRepository.save(savedProduct));
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        existingProduct.setProductName(productDTO.getProductName());
        existingProduct.setQuantity(productDTO.getQuantity());
        existingProduct.setQrCode(productDTO.getQrCode());
        Department department = DepartmentMapper.toEntity(departmentService.getDepartmentById(productDTO.getDepartmentId()));
        existingProduct.setDepartment(department);
        return ProductMapper.toDTO(productRepository.save(existingProduct));
    }

    private String generateQRCodeForProduct(Product product) {
        try {
            Company company = product.getDepartment().getCompany();
            byte[] companyLogo = company.getLogo();

            String productUrl = baseUrl + "/products/" + product.getId();

            return qrCodeService.generateQRCodeWithLogoBase64(productUrl, company.getName(), 200, 200, companyLogo);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Failed to generate QR code", e);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toDTO)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}
