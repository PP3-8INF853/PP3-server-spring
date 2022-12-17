package com.example.authenticationservice.controller;

import com.example.authenticationservice.dto.CustomerLoginDTO;
import com.example.authenticationservice.dto.CustomerSignUpDTO;
import com.example.authenticationservice.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping(value = "", produces = "application/json")
    public @ResponseBody ResponseEntity<Object> getCustomers(){
        return new ResponseEntity<>(this.customerService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "signUp", produces = "application/json")
    public @ResponseBody ResponseEntity<Object> signup(@RequestBody CustomerSignUpDTO customerSignUpDTO){
        return new ResponseEntity<>(this.customerService.create(customerSignUpDTO), HttpStatus.OK);
    }

    @PostMapping(value = "login", produces = "application/json")
    public @ResponseBody ResponseEntity<Object> login(@RequestBody CustomerLoginDTO customerLoginDTO){
        return new ResponseEntity<>(this.customerService.login(customerLoginDTO), HttpStatus.OK);
    }
}
