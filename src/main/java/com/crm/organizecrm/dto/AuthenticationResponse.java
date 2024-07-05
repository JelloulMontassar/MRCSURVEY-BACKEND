    package com.crm.organizecrm.dto;


    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import com.crm.organizecrm.enumirators.Role;
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class AuthenticationResponse {
        private String token;
        private String messageResponse;
        private Role role;
        private String email;
    }
