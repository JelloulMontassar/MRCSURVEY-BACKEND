package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.exception.ProductNotFoundException;
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

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final QRCodeService qrCodeService;
    private final DepartmentServiceImpl departmentService;
    @Value("${base.url}")
    private String baseUrl;
    @Override
    public Product createProduct(Product product) {
        Department department = departmentService.getDepartmentById(product.getDepartment().getId());
        product.setDepartment(department);
        System.out.println(product.getDepartment().getDepartmentName());
        Product savedProduct = productRepository.save(product);
        System.out.println(savedProduct.getDepartment().getDepartmentName());
        savedProduct.setQrCode(generateQRCodeForProduct(savedProduct));
        return productRepository.save(savedProduct);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        existingProduct.setProductName(product.getProductName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setDepartment(product.getDepartment());
        return productRepository.save(existingProduct);
    }

    private String generateQRCodeForProduct(Product product) {
        try {
            Company company = product.getDepartment().getCompany();
            byte[] companyLogo = company.getLogo();

            String productUrl = baseUrl + "/products/" + product.getId();

            return qrCodeService.generateQRCodeWithLogoBase64(productUrl,company.getName(), 200, 200, companyLogo);
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
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
