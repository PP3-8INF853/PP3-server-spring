package com.example.authenticationservice.repository;

import com.example.authenticationservice.entities.Compte;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service")
public interface AccountRepository {
    @GetMapping("/accounts/numero/{numero}")
    public Compte accountByNumero(@PathVariable("numero") String numero);

    @GetMapping("/accounts/{id}")
    public Compte accountById(@PathVariable String id);


    @PostMapping("/accounts")
    public String saveAccount (@RequestBody Compte compte);

    @PutMapping("/accounts/{id}")
    public String updateAccount (@PathVariable("id") String id, @RequestBody Compte compte);
}
