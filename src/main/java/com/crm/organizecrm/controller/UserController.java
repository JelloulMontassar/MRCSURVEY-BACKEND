package com.crm.organizecrm.controller;

import com.crm.organizecrm.dto.RegisterRequest;
import com.crm.organizecrm.dto.RegisterResponse;
import com.crm.organizecrm.model.User;
import com.crm.organizecrm.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.crm.organizecrm.enumirators.Role;
import java.io.IOException;
import java.util.List;
import com.crm.organizecrm.exception.UserException;
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {
    @Autowired
    private final UserServiceImpl userService;
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/create-hr")
    public ResponseEntity<RegisterResponse> registerHR(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse  = new RegisterResponse();
        try {
            userService.registerAccount(registerRequest, Role.HR);
            registerResponse.setEmailResponse(registerRequest.getEmail());
            registerResponse.setMessageResponse("Account Created");
            return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
        }catch (UserException e) {
            registerResponse.setMessageResponse(e.getMessage());
            registerResponse.setEmailResponse(registerRequest.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(registerResponse);
        } catch (Exception e) {
            registerResponse.setMessageResponse(e.getMessage());
            registerResponse.setEmailResponse(registerRequest.getEmail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(registerResponse);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @PostMapping("/profile/image")
    public ResponseEntity<Void> uploadImage(@RequestParam MultipartFile file, Authentication authentication) throws IOException {
        User user = userService.getUserByEmail(authentication.getName());
        user.setProfileImage(file.getBytes());
        userService.updateUser(user.getId(), user);
        return ResponseEntity.ok().build();
    }
}
