package com.example.statementservice.services;

import com.example.statementservice.models.Compte;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service")
public interface AccountRestClient {
    @GetMapping("/accounts/{id}")
    public Compte accountById(@PathVariable String id);
}
