package com.crm.organizecrm.dto;

import com.crm.organizecrm.enumirators.Role;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
public class EmployeeDTO {

    private Long id;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Long userId;
    @Lob
    private byte[] profileImage;
    private List<Long> clientIds;
    private List<Long> transactionIds;
    private List<Long> stockedProductIds;
    private Long companyId;
    private String companyName;
    private Role role;
}
