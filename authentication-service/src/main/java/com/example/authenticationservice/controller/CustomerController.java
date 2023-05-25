package com.example.authenticationservice.controller;

import com.example.authenticationservice.dto.CustomerLoginDTO;
import com.example.authenticationservice.dto.CustomerSignUpDTO;
import com.example.authenticationservice.service.CustomerService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping(value = "findById/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<Object> getCustomerById( @PathVariable String id){
        return new ResponseEntity<>(this.customerService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "findCustomerAccount/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<Object> getCustomerAccount( @PathVariable String id){
        return new ResponseEntity<>(this.customerService.findCustomerAccount(id), HttpStatus.OK);
    }

    @PostMapping(value = "signUp", produces = "application/json")
    public @ResponseBody ResponseEntity<Object> signup( @RequestBody String json){
        CustomerSignUpDTO customerSignUpDTO = new CustomerSignUpDTO();

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            customerSignUpDTO = objectMapper.readValue(json, CustomerSignUpDTO.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(this.customerService.create(customerSignUpDTO), HttpStatus.OK);
    }

    @PostMapping(value = "login",  produces = "application/json")
    public @ResponseBody ResponseEntity<Object> login(@RequestBody String json){
        CustomerLoginDTO customerLoginDTO = new CustomerLoginDTO();

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            customerLoginDTO = objectMapper.readValue(json, CustomerLoginDTO.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        HashMap<String, String> loginValue = new HashMap<>();
        loginValue.put("userId", this.customerService.login(customerLoginDTO));
        return new ResponseEntity<>(loginValue, HttpStatus.OK);
    }
}
