package com.crm.organizecrm.service;

import com.crm.organizecrm.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
}
