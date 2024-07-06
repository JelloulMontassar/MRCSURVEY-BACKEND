package com.crm.organizecrm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Department name cannot be blank")
    @Size(max = 255, message = "Department name must be less than or equal to 255 characters")
    private String departmentName;

    @Size(max = 2000, message = "Department description can be up to 2000 characters")
    private String departmentDescription;

    private Long responsibleEmployeeId;
    private Long companyId;
    private List<Long> productIds;
    private List<Long> employeeIds;
}
