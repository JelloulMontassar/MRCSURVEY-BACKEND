package com.crm.organizecrm.service;

import com.crm.organizecrm.dto.AuthenticationRequest;
import com.crm.organizecrm.dto.AuthenticationResponse;
import com.crm.organizecrm.dto.UserDTO;
import com.crm.organizecrm.enumirators.Role;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO getUserByUsername(String username);
    UserDTO getUserByEmail(String email);
    void resetPassword(long token, String newPassword);
    UserDTO loadUserByUsername(String username);
    void registerAccount(UserDTO userDTO, Role role);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
