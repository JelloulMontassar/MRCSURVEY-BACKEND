package com.crm.organizecrm.controller;

import com.crm.organizecrm.dto.AuthenticationRequest;
import com.crm.organizecrm.dto.AuthenticationResponse;
import com.crm.organizecrm.exception.UserException;
import com.crm.organizecrm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(AuthenticationResponse.builder()
                            .messageResponse("Validation failed")
                            .build());
        }
        try {
            AuthenticationResponse response = userService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (UserException e) {
            log.error("Authentication error: {}", e.getMessage());
            if ("Bad credentials".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(AuthenticationResponse.builder()
                                .messageResponse("User not found")
                                .build());
            } else if ("User is disabled".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(AuthenticationResponse.builder()
                                .messageResponse("User account is not active. Please confirm your email.")
                                .build());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(AuthenticationResponse.builder()
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
