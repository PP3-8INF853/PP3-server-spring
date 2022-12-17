package com.example.statementservice.web;

import com.example.statementservice.entities.ReleveBancaire;
import com.example.statementservice.services.StatementService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

@AllArgsConstructor
public class StatementController {

    StatementService statementService;

    @GetMapping("/statement/{idCompte}")
    public ReleveBancaire getStatement(@PathVariable String idCompte) {
        return statementService.getStatement(idCompte);
    }
}
