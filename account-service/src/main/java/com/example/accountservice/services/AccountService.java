package com.example.accountservice.services;

import com.example.accountservice.entities.Compte;
import com.example.accountservice.repositories.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    AccountRepository accountRepository;

    public ResponseEntity<Compte> getAccount (Long id){
        if (accountRepository.findById(id).isPresent()) {
            Compte compte = accountRepository.findById(id).get();
            return  new ResponseEntity<>(compte, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Compte> updateAccount (Compte compte){
            accountRepository.save(compte);
            return  new ResponseEntity<>(accountRepository.findById(compte.getId()).get(), HttpStatus.OK);
    }
}
