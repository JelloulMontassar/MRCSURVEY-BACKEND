package com.crm.organizecrm.controller;

import com.crm.organizecrm.dto.CompanyDTO;
import com.crm.organizecrm.dto.UserDTO;
import com.crm.organizecrm.mapper.UserMapper;
import com.crm.organizecrm.model.User;
import com.crm.organizecrm.service.CompanyService;
import com.crm.organizecrm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final UserService userService;
    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(
            @Valid @RequestPart("company") CompanyDTO companyDTO,
            BindingResult result,
            @RequestPart(value = "file", required = false) MultipartFile file,
            Authentication authentication) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        UserDTO user = userService.getUserByEmail(authentication.getName());
        companyDTO.setHrUserId(user.getId());

        // If a file is uploaded, convert it to byte[] and set it in the companyDTO
        if (file != null && !file.isEmpty()) {
            try {
                companyDTO.setLogo(file.getBytes());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        return ResponseEntity.ok(companyService.createCompany(companyDTO));
    }
    @GetMapping("/loggedInHr")
    public ResponseEntity<CompanyDTO> getCompanyByHrUser(Authentication authentication) {
        UserDTO user = userService.getUserByEmail(authentication.getName());
        return ResponseEntity.ok(companyService.getCompanyByHrUser(UserMapper.toEntity(user)));
    }
    @GetMapping("/companyExist")
    public ResponseEntity<Boolean> companyExist(Authentication authentication) {
        UserDTO user = userService.getUserByEmail(authentication.getName());
        return ResponseEntity.ok(companyService.companyExist(UserMapper.toEntity(user)));
    }
    @PutMapping("/update")
    public ResponseEntity<CompanyDTO> updateCompanyByHrUser(
            @RequestPart("company") @Valid CompanyDTO companyDTO,
            @RequestPart(value = "file", required = false) MultipartFile file,
            BindingResult result, Authentication authentication) throws IOException {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        UserDTO user = userService.getUserByEmail(authentication.getName());
        System.out.println(Arrays.toString(file.getBytes()));
            companyDTO.setLogo(file.getBytes());
        System.out.println(Arrays.toString(companyDTO.getLogo()));
        System.out.println(companyDTO.getHrUserId());
        System.out.println(companyDTO.getName());

        return ResponseEntity.ok(companyService.updateCompany(companyService.getCompanyByHrUser(UserMapper.toEntity(user)).getId(), companyDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyDTO companyDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(companyService.updateCompany(id, companyDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CompanyDTO>> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }
}
