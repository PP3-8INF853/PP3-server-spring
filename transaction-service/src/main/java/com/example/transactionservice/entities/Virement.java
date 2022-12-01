package com.example.transactionservice.entities;

import com.example.transactionservice.enums.StatutVirement;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
public class Virement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double montant;
    public StatutVirement statut;
    private Long idCompteEmetteur;
    private Long idCompteRecepteur;
    private String question;
    private String reponse;

}
