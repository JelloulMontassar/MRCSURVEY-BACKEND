package com.crm.organizecrm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Builder
public class ClientDTO {

    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255, message = "Name must be less than or equal to 255 characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    //@Pattern(regexp = "\\d{8}", message = "Phone number must be 8 digits") // 8 digits for tunisian phone numbers only
    @Size(min = 4,max = 15, message = "Phone number can be between 4 and 15 characters") // 4 to 15 digits for international phone numbers
    private String phone;

    private Long contactId;
    private Long companyId;
}
