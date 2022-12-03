package com.example.statementservice.services;

import com.example.statementservice.entities.ReleveBancaire;
import com.example.statementservice.models.Compte;
import com.example.statementservice.models.Virement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class StatementService {
    private AccountRestClient compteRestClient ;
    private TransactionRestClient transactionRestClient;
    public ReleveBancaire getStatement(String idCompte) {
        // Retrouve les informations sur le compte bancaire dont l'id est passé en paramètre
        Compte compte = compteRestClient.accountById(idCompte);

        // Retrouve la liste des opérations sur le compte
        List<Virement> operations = transactionRestClient.transactionsByAccountId(compte.getId());

        // Génère un relevé avec les informations récupérées
        return  ReleveBancaire.builder()
                .compteClient(compte)
                .listVirements(operations)
                .dateReleve(LocalDateTime.now())
                .build();
    }
}
