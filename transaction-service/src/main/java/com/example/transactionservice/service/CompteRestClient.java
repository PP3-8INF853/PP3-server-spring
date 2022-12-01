package com.example.transactionservice.service;

import com.example.transactionservice.models.Compte;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account-service")
public interface CompteRestClient {

    @GetMapping("/customers/{id}?projection=fullCustomer")
    public Compte accountById (@PathVariable Long id);

    @PutMapping("/customers/{id}?projection=fullCustomer")
    public Compte updateAccount (@RequestParam Compte compte);
    @GetMapping("/customers/{id}?projection=fullCustomer")
    public void retrieveAmount (@RequestParam Long id, @RequestParam double amount );

    @GetMapping("/customers/{id}?projection=fullCustomer")
    public void addAmount (@RequestParam Long id, @RequestParam double amount );

}
