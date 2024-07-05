package com.crm.organizecrm.mapper;

import com.crm.organizecrm.dto.UserDTO;
import com.crm.organizecrm.model.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .profileImage(user.getProfileImage())
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .enabled(userDTO.isEnabled())
                .profileImage(userDTO.getProfileImage())
                .build();
    }
}
