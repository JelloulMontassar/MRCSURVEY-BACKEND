package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.dto.CompanyDTO;
import com.crm.organizecrm.exception.CompanyNotFoundException;
import com.crm.organizecrm.mapper.CompanyMapper;
import com.crm.organizecrm.model.Company;
import com.crm.organizecrm.repository.CompanyRepository;
import com.crm.organizecrm.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = CompanyMapper.toEntity(companyDTO);
        return CompanyMapper.toDTO(companyRepository.save(company));
    }

    @Override
    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + id));
        existingCompany.setName(companyDTO.getName());
        existingCompany.setLogo(companyDTO.getLogo());
        return CompanyMapper.toDTO(companyRepository.save(existingCompany));
    }

    @Override
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + id));
        companyRepository.delete(company);
    }

    @Override
    public Optional<CompanyDTO> getCompanyById(Long id) {
        return companyRepository.findById(id).map(CompanyMapper::toDTO);
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(CompanyMapper::toDTO)
                .collect(Collectors.toList());
    }
}
