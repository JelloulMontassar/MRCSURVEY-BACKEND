package com.crm.organizecrm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContactDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Long clientId;
}
