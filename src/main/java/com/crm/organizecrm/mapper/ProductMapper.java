package com.crm.organizecrm.mapper;

import com.crm.organizecrm.dto.ProductDTO;
import com.crm.organizecrm.model.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .departmentId(product.getDepartment() != null ? product.getDepartment().getId() : null)
                .qrCode(product.getQrCode())
                .build();
    }

    public static Product toEntity(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .productName(productDTO.getProductName())
                .quantity(productDTO.getQuantity())
                .qrCode(productDTO.getQrCode())
                .build();
    }
}
