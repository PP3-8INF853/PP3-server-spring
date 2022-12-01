package com.example.transactionservice.web;

import com.example.transactionservice.entities.Virement;
import com.example.transactionservice.service.VirementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    VirementService virementService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMoney(
            @RequestParam Virement virement
    ){
        return virementService.sendMoney(virement);
    }

    @PostMapping("/receive")
    public ResponseEntity<String> receiveMoney(
            @RequestParam Long idVirement, @RequestParam String reponse
    ){
        return virementService.receiveMoney(idVirement, reponse);
    }
}
