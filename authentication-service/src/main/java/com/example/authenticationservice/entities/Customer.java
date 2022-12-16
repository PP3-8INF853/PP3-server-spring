package com.example.authenticationservice.entities;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Customer {
    @Id
    public String id;
    public String username;
    public String firstname;
    public String lastname;
    public String password;
    public String email;
    public String phoneNumber;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
    }

    public Customer(String username, String firstname, String lastname,
                    String password, String email, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
