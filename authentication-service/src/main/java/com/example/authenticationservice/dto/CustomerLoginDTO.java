package com.example.authenticationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerLoginDTO {

    @JsonProperty("username")
    public String username;
    @JsonProperty("password")
    public String password;
}
