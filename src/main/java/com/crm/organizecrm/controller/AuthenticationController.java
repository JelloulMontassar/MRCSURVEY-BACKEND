package com.crm.organizecrm.controller;

import java.io.IOException;
import java.util.List;
import com.crm.organizecrm.dto.AuthenticationResponse;
import com.crm.organizecrm.service.JwtService;
import com.crm.organizecrm.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import com.crm.organizecrm.dto.AuthenticationRequest;
import com.crm.organizecrm.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.crm.organizecrm.exception.UserException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Slf4j
public class AuthenticationController {
    @Autowired
    private final UserServiceImpl userService;
    public AuthenticationController(UserServiceImpl userService) {

        this.userService = userService;
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            AuthenticationResponse response = userService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (UserException e) {
            System.out.println(e.getMessage());
            if (e.getMessage().equals("Bad credentials")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        AuthenticationResponse.builder()
                                .messageResponse("User not found")
                                .build());
            } else if (e.getMessage().equals("User is disabled")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                        AuthenticationResponse.builder()
                                .messageResponse("User account is not active. Please confirm your email.")
                                .build());
            }

            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        AuthenticationResponse.builder()
                                .messageResponse("An error occurred during authentication.")
                                .build());
            }
        }
    }
    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestParam long token, @RequestParam String newPassword) {
        userService.resetPassword(token, newPassword);
        return ResponseEntity.ok().build();
    }

}
