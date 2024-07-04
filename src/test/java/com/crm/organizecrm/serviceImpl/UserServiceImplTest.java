package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.OrganizeCrmApplication;
import com.crm.organizecrm.config.PasswordEncoder;
import com.crm.organizecrm.enumirators.Role;
import com.crm.organizecrm.model.User;
import com.crm.organizecrm.repository.UserRepository;
import com.crm.organizecrm.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrganizeCrmApplication.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService ;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder ;

    @Test
    public void testCreateUser() {
        String encodedPassword = passwordEncoder.encode("password");
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword(encodedPassword);
        user.setFirstName("Test");
        user.setLastName("User");
        user.setPhoneNumber("1234567890");
        user.setRole(Role.ADMIN); // Assuming Role is an enum with a value USER
        user.setEnabled(true);
        user.setResetToken(12345L);

        User savedUser = userService.createUser(user);

        Optional<User> foundUser = userRepository.findById(savedUser.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("testuser");
        assertThat(foundUser.get().getEmail()).isEqualTo("testuser@example.com");
        assertThat(passwordEncoder.matches("password",encodedPassword)).isTrue();
        assertThat(foundUser.get().getFirstName()).isEqualTo("Test");
        assertThat(foundUser.get().getLastName()).isEqualTo("User");
        assertThat(foundUser.get().getPhoneNumber()).isEqualTo("1234567890");
        assertThat(foundUser.get().getRole()).isEqualTo(Role.ADMIN);
        assertThat(foundUser.get().isEnabled()).isTrue();
        assertThat(foundUser.get().getResetToken()).isEqualTo(12345L);
    }
}