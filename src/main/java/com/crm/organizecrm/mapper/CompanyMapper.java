package com.crm.organizecrm.mapper;

import com.crm.organizecrm.dto.CompanyDTO;
import com.crm.organizecrm.model.Company;

public class CompanyMapper {

    public static CompanyDTO toDTO(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .logo(company.getLogo())
                .hrUserId(company.getHrUser() != null ? company.getHrUser().getId() : null)
                .build();
    }

    public static Company toEntity(CompanyDTO companyDTO) {
        return Company.builder()
                .id(companyDTO.getId())
                .name(companyDTO.getName())
                .logo(companyDTO.getLogo())
                .build();
    }
}
