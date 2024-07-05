package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.exception.ResourceNotFoundException;
import com.crm.organizecrm.model.Company;
import com.crm.organizecrm.repository.CompanyRepository;
import com.crm.organizecrm.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        existingCompany.setName(company.getName());
        existingCompany.setSubscription(company.getSubscription());
        existingCompany.setHrUser(company.getHrUser());
        existingCompany.setDepartments(company.getDepartments());
        existingCompany.setEmployees(company.getEmployees());
        existingCompany.setContacts(company.getContacts());
        existingCompany.setClients(company.getClients());
        existingCompany.setProducts(company.getProducts());
        return companyRepository.save(existingCompany);
    }

    @Override
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        companyRepository.delete(company);
    }

    @Override
    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
