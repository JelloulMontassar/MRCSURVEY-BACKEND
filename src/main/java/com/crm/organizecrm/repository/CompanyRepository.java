package com.crm.organizecrm.repository;

import com.crm.organizecrm.model.Company;
import com.crm.organizecrm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company getCompanyByHrUser(User hrUser);

    Boolean existsByHrUser(User entity);
}
