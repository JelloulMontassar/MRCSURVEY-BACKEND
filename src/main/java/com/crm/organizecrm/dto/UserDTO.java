package com.crm.organizecrm.dto;

import com.crm.organizecrm.enumirators.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private Role role;
    private boolean enabled;
    private byte[] profileImage;
}
