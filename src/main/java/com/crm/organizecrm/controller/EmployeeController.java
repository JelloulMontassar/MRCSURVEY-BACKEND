package com.crm.organizecrm.controller;

import com.crm.organizecrm.dto.CompanyDTO;
import com.crm.organizecrm.dto.EmployeeDTO;
import com.crm.organizecrm.dto.UserDTO;
import com.crm.organizecrm.mapper.UserMapper;
import com.crm.organizecrm.model.Company;
import com.crm.organizecrm.model.User;
import com.crm.organizecrm.service.CompanyService;
import com.crm.organizecrm.service.EmployeeService;
import com.crm.organizecrm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final UserService userService;
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO, BindingResult result, Authentication authentication){
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        UserDTO hrUser = userService.getUserByEmail(authentication.getName());
        System.out.println(hrUser.getEmail());
        CompanyDTO company = companyService.getCompanyByHrUser(UserMapper.toEntity(hrUser));
        System.out.println(company.getName());
        employeeDTO.setCompanyId(company.getId());
        System.out.println(employeeDTO.getEmail());
        return ResponseEntity.ok(employeeService.createEmployee(employeeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}
