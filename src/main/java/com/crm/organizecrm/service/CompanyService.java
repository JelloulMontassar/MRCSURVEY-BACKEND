package com.crm.organizecrm.service;

import com.crm.organizecrm.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company createCompany(Company company);
    Company updateCompany(Long id, Company company);
    void deleteCompany(Long id);
    Optional<Company> getCompanyById(Long id);
    List<Company> getAllCompanies();
}
