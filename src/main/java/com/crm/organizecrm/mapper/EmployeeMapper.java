package com.crm.organizecrm.mapper;

import com.crm.organizecrm.dto.EmployeeDTO;
import com.crm.organizecrm.model.*;

import java.util.Arrays;
import java.util.Base64;
import java.util.stream.Collectors;

public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee employee) {
        if (employee == null) {
            return null;
        }

        User user = employee.getUser();
        Company company = employee.getCompany();
        String profileImageBase64 = null;

        if (user != null && user.getProfileImage() != null) {
            profileImageBase64 = Base64.getEncoder().encodeToString(user.getProfileImage());
        }

        return EmployeeDTO.builder()
                .id(employee.getId())
                .email(user != null ? user.getEmail() : null)
                .phoneNumber(user != null ? user.getPhoneNumber() : null)
                .firstName(user != null ? user.getFirstName() : null)
                .lastName(user != null ? user.getLastName() : null)
                .role(user != null ? user.getRole() : null)
                .companyName(company != null ? company.getName() : null)
                .userId(user != null ? user.getId() : null)
                .profileImage(profileImageBase64.getBytes())
                .clientIds(employee.getClients().stream().map(Client::getId).collect(Collectors.toList()))
                .transactionIds(employee.getTransactions().stream().map(Transaction::getId).collect(Collectors.toList()))
                .stockedProductIds(employee.getStockedProducts().stream().map(Product::getId).collect(Collectors.toList()))
                .companyId(employee.getCompany() != null ? employee.getCompany().getId() : null)
                .build();
    }

    public static Employee toEntity(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .id(employeeDTO.getId())

                    .build();
    }
}
