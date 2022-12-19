package com.example.transactionservice.web;

import com.example.transactionservice.entities.Virement;
import com.example.transactionservice.entities.VirementReceiveDTO;
import com.example.transactionservice.entities.VirementSendDTO;
import com.example.transactionservice.service.VirementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    VirementService virementService;

    @ResponseBody
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllTransactions(){
        return this.virementService.getAllTransactions();
    }

    @ResponseBody
    @PostMapping("/send")
    public ResponseEntity<Object> sendMoney(
            @RequestBody String json
    ){
        VirementSendDTO virementSendDTO = new VirementSendDTO();

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            virementSendDTO = objectMapper.readValue(json, VirementSendDTO.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        return virementService.sendMoney(virementSendDTO);
    }

    @ResponseBody
    @PostMapping("/receive/{idVirement}")
    public ResponseEntity<String> receiveMoney(
            @PathVariable String idVirement, @RequestBody VirementReceiveDTO reponse
    ){
        return virementService.receiveMoney(idVirement, reponse);
    }

    @ResponseBody
    @GetMapping("/transactions/{id}")
    public ResponseEntity<List<Virement>> findTransactionsByAccountId (
            @PathVariable String id
    )
    {
        return virementService.findTransactionsByAccountId(id);
    }
}
