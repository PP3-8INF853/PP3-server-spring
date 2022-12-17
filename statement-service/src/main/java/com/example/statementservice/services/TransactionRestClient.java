package com.example.statementservice.services;

import com.example.statementservice.models.Compte;
import com.example.statementservice.models.Virement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "transaction-service")
public interface TransactionRestClient {
    @GetMapping("/transactions/{id}")
    public List<Virement> transactionsByAccountId(@PathVariable String id);

}
