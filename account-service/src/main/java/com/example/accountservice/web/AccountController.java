package com.example.accountservice.web;

import com.example.accountservice.entities.Compte;

import com.example.accountservice.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class AccountController {
    AccountService accountService;

    @ResponseBody
    @GetMapping("/accounts/{id}")
    public Compte findAccountById (@PathVariable String id){
      return accountService.findAccountById(id);
    }

    @ResponseBody
    @GetMapping("/accounts/numero/{numero}")
    public Compte findAccountByNumero (@PathVariable String numero){
        return accountService.findAccountByNumero(numero);
    }

    @ResponseBody
    @PostMapping("/accounts")
    public String saveAccount (@RequestBody Compte compte){
     return accountService.saveAccount(compte);
    }

    @ResponseBody
    @PutMapping("/accounts/{id}")
    public String updateAccount (@PathVariable String id, @RequestBody Compte compte){
        return accountService.updateAccount(id, compte);
    }
}
