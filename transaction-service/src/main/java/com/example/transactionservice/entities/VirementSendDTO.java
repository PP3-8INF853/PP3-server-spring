package com.example.transactionservice.entities;

import com.example.transactionservice.enums.StatutVirement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor @Builder
public class VirementSendDTO {
    private double montant;
    private String numCompteEmetteur;
    private String numCompteRecepteur;
    private String question;
    private String reponse;
}
