package com.crm.organizecrm.mapper;

import com.crm.organizecrm.dto.EmployeeDTO;
import com.crm.organizecrm.model.Employee;

import java.util.stream.Collectors;

public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .userId(employee.getUser() != null ? employee.getUser().getId() : null)
                .clientIds(employee.getClients() != null ? employee.getClients().stream().map(client -> client.getId()).collect(Collectors.toList()) : null)
                .transactionIds(employee.getTransactions() != null ? employee.getTransactions().stream().map(transaction -> transaction.getId()).collect(Collectors.toList()) : null)
                .stockedProductIds(employee.getStockedProducts() != null ? employee.getStockedProducts().stream().map(product -> product.getId()).collect(Collectors.toList()) : null)
                .build();
    }

    public static Employee toEntity(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .id(employeeDTO.getId())
                .name(employeeDTO.getName())
                .email(employeeDTO.getEmail())
                .build();
    }
}
