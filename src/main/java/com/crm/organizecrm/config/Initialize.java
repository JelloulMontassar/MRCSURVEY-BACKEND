package com.crm.organizecrm.config;

import com.crm.organizecrm.dto.UserDTO;
import com.crm.organizecrm.enumirators.Role;
import com.crm.organizecrm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Initialize implements CommandLineRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        try {
            createAdminUserIfNeeded();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void createAdminUserIfNeeded() {
        UserDTO adminUser = null;
        try {
            adminUser = userService.getUserByEmail("admin@admin.com");
        } catch (Exception e) {
            adminUser = UserDTO.builder()
                    .firstName("admin")
                    .lastName("admin")
                    .email("admin@admin.com")
                    .phoneNumber("88888888")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ADMIN)
                    .enabled(true)
                    .build();
            userService.createUser(adminUser);
        }
    }
}
