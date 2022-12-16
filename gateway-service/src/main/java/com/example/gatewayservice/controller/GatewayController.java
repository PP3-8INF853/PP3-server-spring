package com.example.gatewayservice.controller;

import jakarta.servlet.ServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GatewayController {

    public GatewayController(){

    }

    @GetMapping(value = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String manageGetCustomer(){

        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/customers").toUriString();
    }

    @RequestMapping(value ="/customers",produces = {MediaType.APPLICATION_JSON_VALUE})
    public String managePostCustomer(ServletRequest request){
        return request.getLocalName();
    }
}
