package com.crm.organizecrm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Builder
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    @Size(max = 255, message = "Product name must be less than or equal to 255 characters")
    private String productName;

    @NotBlank(message = "Quantity cannot be blank")
    @Size(max = 50, message = "Quantity must be less than or equal to 50 characters")
    private String quantity;

    private Long departmentId;

    @Size(max = 102400, message = "QR code must be less than or equal to 102400 characters")
    private String qrCode;
}
