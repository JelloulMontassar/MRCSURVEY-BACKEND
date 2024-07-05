package com.crm.organizecrm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private Long userId;
    private List<Long> clientIds;
    private List<Long> transactionIds;
    private List<Long> stockedProductIds;
}
