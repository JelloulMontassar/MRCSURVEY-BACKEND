package com.crm.organizecrm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DepartmentDTO {
    private Long id;
    private String departmentName;
    private String departmentDescription;
    private Long responsibleEmployeeId;
    private Long companyId;
    private List<Long> productIds;
    private List<Long> employeeIds;
}
