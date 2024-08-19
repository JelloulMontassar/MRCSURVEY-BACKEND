package com.crm.organizecrm.dto;

import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Builder
public class CompanyDTO {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String website;
    private String description;
    @Lob
    private byte[] logo;
    private Long hrUserId;
}
