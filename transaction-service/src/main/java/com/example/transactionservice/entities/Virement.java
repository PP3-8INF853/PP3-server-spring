package com.example.transactionservice.entities;

import com.example.transactionservice.enums.StatutVirement;

import com.example.transactionservice.models.Compte;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@AllArgsConstructor @NoArgsConstructor @Builder
public class Virement {
    @Id
    private String id;
    private double montant;
    public StatutVirement statut;
    private String idCompteEmetteur;
    private Compte compteEmetteur;
    private String idCompteRecepteur;
    private Compte compteRecepteur;
    private String question;
    private String reponse;
    private LocalDateTime dateEnvoi;
    private LocalDateTime dateReception;

}
