package com.example.transactionservice.entities;

import com.example.transactionservice.enums.StatutVirement;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("montant")
    private double montant;

    @JsonAlias({"idEmeteur", "numCompteEmetteur"})
    private String numCompteEmetteur;

    @JsonAlias({"idRecepteur", "numCompteRecepteur"})
    private String numCompteRecepteur;
    @JsonProperty("question")
    private String question;
    @JsonProperty("reponse")
    private String reponse;
}
