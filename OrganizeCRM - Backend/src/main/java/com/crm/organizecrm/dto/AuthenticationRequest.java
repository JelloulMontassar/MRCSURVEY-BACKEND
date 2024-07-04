package com.crm.organizecrm.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    @JsonProperty("email")
    private String email ;
    @JsonProperty("password")
    private String password ;
}
