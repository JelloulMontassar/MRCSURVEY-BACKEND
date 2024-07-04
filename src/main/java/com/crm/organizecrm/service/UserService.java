package com.crm.organizecrm.service;

import com.crm.organizecrm.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    List<User> getAllUsers();
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    void resetPassword(long token, String newPassword);
    User loadUserByUsername(String username);
}
