package com.crm.organizecrm.model;

import com.crm.organizecrm.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;



@DataJpaTest
class DepartmentTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testCreateDepartment() {
        Department department = new Department();
        department.setDepartmentName("IT");
        department.setDepartmentDescription("Information Technology");

        Department savedDepartment = departmentRepository.save(department);

        assertThat(savedDepartment).isNotNull();
        assertThat(savedDepartment.getId()).isNotNull();
        assertThat(savedDepartment.getDepartmentName()).isEqualTo("IT");
        assertThat(savedDepartment.getDepartmentDescription()).isEqualTo("Information Technology");
    }

    @Test
    public void testReadDepartment() {
        Department department = new Department();
        department.setDepartmentName("IT");
        department.setDepartmentDescription("Information Technology");

        Department savedDepartment = departmentRepository.save(department);

        Optional<Department> retrievedDepartment = departmentRepository.findById(savedDepartment.getId());

        assertThat(retrievedDepartment).isPresent();
        assertThat(retrievedDepartment.get().getDepartmentName()).isEqualTo("IT");
        assertThat(retrievedDepartment.get().getDepartmentDescription()).isEqualTo("Information Technology");
    }

    @Test
    public void testUpdateDepartment() {
        Department department = new Department();
        department.setDepartmentName("IT");
        department.setDepartmentDescription("Information Technology");

        Department savedDepartment = departmentRepository.save(department);

        savedDepartment.setDepartmentName("HR");
        savedDepartment.setDepartmentDescription("Human Resources");

        Department updatedDepartment = departmentRepository.save(savedDepartment);

        assertThat(updatedDepartment).isNotNull();
        assertThat(updatedDepartment.getDepartmentName()).isEqualTo("HR");
        assertThat(updatedDepartment.getDepartmentDescription()).isEqualTo("Human Resources");
    }

    @Test
    public void testDeleteDepartment() {
        Department department = new Department();
        department.setDepartmentName("IT");
        department.setDepartmentDescription("Information Technology");

        Department savedDepartment = departmentRepository.save(department);
        Long departmentId = savedDepartment.getId();

        departmentRepository.delete(savedDepartment);

        Optional<Department> retrievedDepartment = departmentRepository.findById(departmentId);

        assertThat(retrievedDepartment).isNotPresent();
    }
}