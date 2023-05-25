package com.example.authenticationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerSignUpDTO {

    @JsonProperty("username")
    public String username;
    @JsonProperty("firstname")
    public String firstname;
    @JsonProperty("lastname")
    public String lastname;
    @JsonProperty("password")
    public String password;
    @JsonProperty("email")
    public String email;
    @JsonProperty("phoneNumber")
    public String phoneNumber;
}
