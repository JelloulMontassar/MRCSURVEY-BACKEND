package com.crm.organizecrm.repository;

import com.crm.organizecrm.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
