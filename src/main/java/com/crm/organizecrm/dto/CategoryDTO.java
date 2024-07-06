package com.crm.organizecrm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Builder
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Type cannot be blank")
    @Size(max = 255, message = "Type must be less than or equal to 255 characters")
    private String type;
}
