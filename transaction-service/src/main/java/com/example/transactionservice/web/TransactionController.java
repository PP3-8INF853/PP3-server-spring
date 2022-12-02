package com.example.transactionservice.web;

import com.example.transactionservice.entities.Virement;
import com.example.transactionservice.models.TransactionAnswer;
import com.example.transactionservice.service.VirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    VirementService virementService;

    @ResponseBody
    @PostMapping("/send")
    public ResponseEntity<String> sendMoney(
            @RequestBody Virement virement
    ){
        return virementService.sendMoney(virement);
    }

    @ResponseBody
    @PostMapping("/receive/{idVirement}")
    public ResponseEntity<String> receiveMoney(
            @PathVariable String idVirement, @RequestBody TransactionAnswer reponse
    ){
        return virementService.receiveMoney(idVirement, reponse);
    }
}
