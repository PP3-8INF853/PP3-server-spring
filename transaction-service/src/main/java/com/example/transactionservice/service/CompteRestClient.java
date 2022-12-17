package com.example.transactionservice.service;

import com.example.transactionservice.models.Compte;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service")
public interface CompteRestClient {

    @GetMapping("/accounts/numero/{numero}")
    public Compte accountByNumero(@PathVariable String numero);

    @GetMapping("/accounts/{id}")
    public Compte accountById(@PathVariable String id);


    @PostMapping("/accounts")
    public String saveAccount (@RequestBody Compte compte);

    @PutMapping("/accounts/{id}")
    public String updateAccount (@PathVariable String id, @RequestBody Compte compte);


}
