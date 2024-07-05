package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.dto.AuthenticationRequest;
import com.crm.organizecrm.dto.AuthenticationResponse;
import com.crm.organizecrm.dto.UserDTO;
import com.crm.organizecrm.exception.TokenNotFoundOrIncorrectExeption;
import com.crm.organizecrm.exception.UserException;
import com.crm.organizecrm.exception.UserNotFoundException;
import com.crm.organizecrm.mapper.UserMapper;
import com.crm.organizecrm.model.User;
import com.crm.organizecrm.repository.UserRepository;
import com.crm.organizecrm.service.JwtService;
import com.crm.organizecrm.service.UserService;
import com.crm.organizecrm.config.PasswordEncoder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import com.crm.organizecrm.enumirators.Role;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(userDTO.getPassword()));
        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(userDTO.getPassword()));
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        existingUser.setRole(userDTO.getRole());
        existingUser.setEnabled(userDTO.isEnabled());
        existingUser.setProfileImage(userDTO.getProfileImage());
        return UserMapper.toDTO(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public void resetPassword(long token, String newPassword) {
        User user = userRepository.findByResetToken(token)
                .orElseThrow(() -> new TokenNotFoundOrIncorrectExeption("Invalid reset token"));
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public UserDTO loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }

    @Override
    public void registerAccount(UserDTO userDTO, Role role) {
        boolean userExists = userRepository.findByEmail(userDTO.getEmail()).isPresent();
        if (userExists) {
            throw new UserException("A user already exists with the same email");
        }
        User user = UserMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.setRole(role);
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new UserException(e.getMessage());
        }
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + request.getEmail()));
        Map<String, String> map = new HashMap<>();
        map.put("role", user.getRole().name());
        String jwtToken = jwtService.genToken(user, map);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole())
                .email(user.getEmail())
                .messageResponse("You have been successfully authenticated!")
                .build();
    }
}
