package com.crm.organizecrm.mapper;

import com.crm.organizecrm.dto.DepartmentDTO;
import com.crm.organizecrm.model.Department;

import java.util.stream.Collectors;

public class DepartmentMapper {

    public static DepartmentDTO toDTO(Department department) {
        return DepartmentDTO.builder()
                .id(department.getId())
                .departmentName(department.getDepartmentName())
                .departmentDescription(department.getDepartmentDescription())
                .responsibleEmployeeId(department.getResponsibleEmployee() != null ? department.getResponsibleEmployee().getId() : null)
                .companyId(department.getCompany() != null ? department.getCompany().getId() : null)
                .productIds(department.getProducts() != null ? department.getProducts().stream().map(product -> product.getId()).collect(Collectors.toList()) : null)
                .employeeIds(department.getEmployees() != null ? department.getEmployees().stream().map(employee -> employee.getId()).collect(Collectors.toList()) : null)
                .build();
    }

    public static Department toEntity(DepartmentDTO departmentDTO) {
        return Department.builder()
                .id(departmentDTO.getId())
                .departmentName(departmentDTO.getDepartmentName())
                .departmentDescription(departmentDTO.getDepartmentDescription())
                .build();
    }
}
