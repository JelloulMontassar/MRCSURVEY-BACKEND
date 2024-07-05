package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.exception.DepartmentNotFoundException;
import com.crm.organizecrm.model.Department;
import com.crm.organizecrm.repository.DepartmentRepository;
import com.crm.organizecrm.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));
        existingDepartment.setDepartmentName(department.getDepartmentName());
        existingDepartment.setDepartmentDescription(department.getDepartmentDescription());
        existingDepartment.setProducts(department.getProducts());
        existingDepartment.setEmployees(department.getEmployees());
        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));
        departmentRepository.delete(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
