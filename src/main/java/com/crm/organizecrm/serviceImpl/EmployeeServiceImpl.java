package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.dto.EmployeeDTO;
import com.crm.organizecrm.enumirators.Role;
import com.crm.organizecrm.exception.EmployeeNotFoundException;
import com.crm.organizecrm.mapper.EmployeeMapper;
import com.crm.organizecrm.model.Company;
import com.crm.organizecrm.model.Employee;
import com.crm.organizecrm.model.User;
import com.crm.organizecrm.repository.CompanyRepository;
import com.crm.organizecrm.repository.EmployeeRepository;
import com.crm.organizecrm.repository.UserRepository;
import com.crm.organizecrm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        User user = new User() ;

        user.setRole(employeeDTO.getRole());
        user.setPassword("Azerty123@#");
        user.setUsername(employeeDTO.getEmail());
        user.setFirstName(employeeDTO.getFirstName());
        user.setLastName(employeeDTO.getLastName());
        user.setEmail(employeeDTO.getEmail());
        user.setPhoneNumber(employeeDTO.getPhoneNumber());
        user.setEnabled(true);
        user.setProfileImage(employeeDTO.getProfileImage());
        User savedUser = userRepository.save(user);
        Company company = companyRepository.findById(employeeDTO.getCompanyId())
                .orElseThrow(() -> new EmployeeNotFoundException("Company not found with id: " + employeeDTO.getCompanyId()));
        Employee employee = new Employee();
        employee.setUser(savedUser);
        employee.setCompany(company);
        return EmployeeMapper.toDTO(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

        return EmployeeMapper.toDTO(employeeRepository.save(existingEmployee));
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(EmployeeMapper::toDTO)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
