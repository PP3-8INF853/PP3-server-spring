package com.example.accountservice.web;

import com.example.accountservice.entities.Compte;

import com.example.accountservice.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



public class AccountController {

    AccountService accountService;

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Compte> getAccount (@PathVariable Long id){
      return accountService.getAccount(id);
    }

    @PutMapping("/accounts")
    public ResponseEntity<Compte> updateAccount (@RequestParam Compte compte){
     return accountService.updateAccount(compte);
    }


}
