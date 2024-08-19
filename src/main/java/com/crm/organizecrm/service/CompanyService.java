package com.crm.organizecrm.service;

import com.crm.organizecrm.dto.CompanyDTO;
import com.crm.organizecrm.model.User;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    CompanyDTO createCompany(CompanyDTO companyDTO);
    CompanyDTO updateCompany(Long id, CompanyDTO companyDTO);
    void deleteCompany(Long id);
    Optional<CompanyDTO> getCompanyById(Long id);
    List<CompanyDTO> getAllCompanies();
    CompanyDTO getCompanyByHrUser(User hrUser);

    Boolean companyExist(User entity);
}
