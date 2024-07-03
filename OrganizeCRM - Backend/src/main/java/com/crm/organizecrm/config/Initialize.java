package com.crm.organizecrm;

import com.crm.organizecrm.model.User;
import com.crm.organizecrm.service.UserService;
import com.crm.organizecrm.enumirators.Role;
import com.crm.organizecrm.config.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        User adminUser = userService.getUserByEmail("admin@admin.com");
        if (adminUser == null) {
            adminUser = new User();
            adminUser.setFirstName("admin");
            adminUser.setLastName("admin");
            adminUser.setEmail("admin@admin.com");
            adminUser.setPhoneNumber("88888888");
            adminUser.setPassword(passwordEncoder.bCryptPasswordEncoder().encode("admin"));
            adminUser.setRole(Role.ADMIN);
            adminUser.setEnabled(true);
            userService.createUser(adminUser);
        }
    }
}
