package com.example.statementservice.models;

import lombok.Data;

@Data
public class Virement {
    private String id;
    private double montant;
    public String statut;
  //  private String idCompteEmetteur;
    private Compte compteEmetteur;
  //  private String idCompteRecepteur;
    private Compte compteRecepteur;
    private String question;
    private String reponse;
}
