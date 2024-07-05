package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.dto.DepartmentDTO;
import com.crm.organizecrm.exception.DepartmentNotFoundException;
import com.crm.organizecrm.mapper.DepartmentMapper;
import com.crm.organizecrm.model.Department;
import com.crm.organizecrm.repository.DepartmentRepository;
import com.crm.organizecrm.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        Department department = DepartmentMapper.toEntity(departmentDTO);
        return DepartmentMapper.toDTO(departmentRepository.save(department));
    }

    @Override
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));
        existingDepartment.setDepartmentName(departmentDTO.getDepartmentName());
        existingDepartment.setDepartmentDescription(departmentDTO.getDepartmentDescription());
        return DepartmentMapper.toDTO(departmentRepository.save(existingDepartment));
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));
        departmentRepository.delete(department);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .map(DepartmentMapper::toDTO)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + id));
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
