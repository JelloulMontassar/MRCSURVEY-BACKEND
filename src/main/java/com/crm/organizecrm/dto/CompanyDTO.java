package com.crm.organizecrm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CompanyDTO {
    private Long id;
    private String name;
    private byte[] logo;
    private Long hrUserId;
}
