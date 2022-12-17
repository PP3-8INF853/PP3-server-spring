package com.example.accountservice.services;

import com.example.accountservice.entities.Compte;
import com.example.accountservice.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AccountService {
    AccountRepository accountRepository;

    public Compte findAccountById (String id){
        return  accountRepository.findById(id).get();
    }

    public Compte findAccountByNumero (String numero){
        log.info("Récupération du compte numero : " +numero);
        return  accountRepository.findByNumero(numero).get();
    }
    public String saveAccount (Compte compte){
        try {
            return accountRepository.save(compte).getId();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String updateAccount(String id, Compte compte) {
        Optional<Compte> oldCompteOptional = accountRepository.findById(id);
        if (oldCompteOptional.isPresent()){
            Compte compteToSave = oldCompteOptional.get();
            compteToSave.setNumero(compte.getNumero() != null ? compte.getNumero() : compteToSave.getNumero() );
            compteToSave.setSolde(compte.getSolde());
            return  accountRepository.save(compteToSave).getId();
        }
        else {
            throw new NoSuchElementException();
        }

    }
}
