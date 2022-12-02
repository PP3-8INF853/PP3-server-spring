package com.example.transactionservice.entities;

import com.example.transactionservice.enums.StatutVirement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor @NoArgsConstructor
public class Virement {
    @Id
    private String id;
    private double montant;
    public StatutVirement statut;
    private String numCompteEmetteur;
    private String numCompteRecepteur;
    private String question;
    private String reponse;

}
