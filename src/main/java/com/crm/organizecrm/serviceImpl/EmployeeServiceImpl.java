package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.dto.EmployeeDTO;
import com.crm.organizecrm.exception.UserNotFoundException;
import com.crm.organizecrm.mapper.EmployeeMapper;
import com.crm.organizecrm.model.Employee;
import com.crm.organizecrm.repository.EmployeeRepository;
import com.crm.organizecrm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        return EmployeeMapper.toDTO(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Employee not found with id: " + id));
        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setEmail(employeeDTO.getEmail());
        return EmployeeMapper.toDTO(employeeRepository.save(existingEmployee));
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(EmployeeMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
