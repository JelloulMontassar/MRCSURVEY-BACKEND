package com.crm.organizecrm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.crm.organizecrm.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import com.crm.organizecrm.dto.AuthenticationRequest;
import com.crm.organizecrm.model.User;
import com.crm.organizecrm.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {

    @Autowired
    private JwtService jwtUtil;
    private final UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthenticationRequest authRequest) throws Exception {
        try {
            log.info("username :{} password : {}",authRequest.getEmail(),authRequest.getPassword());

            Authentication authentication =authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            //fixed token generation (error was application.properties missing jwt.secret value)
            String token = jwtUtil.genToken(userDetails, new HashMap<>());

            return token ;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("inavalid username/password");

        }



    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
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

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestParam long token, @RequestParam String newPassword) {
        userService.resetPassword(token, newPassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/profile/image")
    public ResponseEntity<Void> uploadImage(@RequestParam MultipartFile file, Authentication authentication) throws IOException {
        User user = userService.getUserByEmail(authentication.getName());
        user.setProfileImage(file.getBytes());
        userService.updateUser(user.getId(), user);
        return ResponseEntity.ok().build();
    }
}
