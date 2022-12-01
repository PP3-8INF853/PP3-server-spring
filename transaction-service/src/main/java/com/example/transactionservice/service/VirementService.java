package com.example.transactionservice.service;

import com.example.transactionservice.entities.Virement;
import com.example.transactionservice.enums.StatutVirement;
import com.example.transactionservice.models.Compte;
import com.example.transactionservice.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VirementService {

    private CompteRestClient compteRestClient ;
    private TransactionRepository transactionRepository;

    public ResponseEntity<String> sendMoney(Virement virement) {
        Compte compteEmetteur = compteRestClient.accountById(virement.getIdCompteEmetteur());

        // Vérifie si le compte emetteur a un solde suffisant pour l'envoi
        if (compteEmetteur.getSolde()>=virement.getMontant()) {

            // Modifie le solde du client emetteur
            compteEmetteur.setSolde(compteEmetteur.getSolde()-virement.getMontant());
            compteRestClient.updateAccount(compteEmetteur);

            // Modifie le statut du virement
            virement.setStatut(StatutVirement.EN_COURS);
            transactionRepository.save(virement);
            return new ResponseEntity<>("Virement effectué avec succès. En attente de la confirmation du récepteur", HttpStatus.OK);
        }
        virement.setStatut(StatutVirement.ECHEC);
        transactionRepository.save(virement);
        return new ResponseEntity<>("Solde du compte émetteur insuffisant", HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<String> receiveMoney(Long idVirement, String reponse) {
        // Récupère le virement dont l'id est passé en paramètre
        Virement virement = transactionRepository.findById(idVirement).get();

        // Retourne une réponse négative si le virement n'existe pas
        if (virement.equals(null))
            return new ResponseEntity<> ("Le virement recherché est introuvable", HttpStatus.NOT_FOUND);

        Compte compteRecepteur = compteRestClient.accountById(virement.getIdCompteRecepteur());

        // Vérifie si la réponse à la question du virement est la bonne
        if (virement.getReponse()==reponse){

            // Modifie le solde du client récepteur
            compteRecepteur.setSolde(compteRecepteur.getSolde()+virement.getMontant());
            compteRestClient.updateAccount(compteRecepteur);

            // Modifie le statut du virement
            virement.setStatut(StatutVirement.EFFECTUE);
            /* TODO: Ajout ou update dans la base de données Virement à l'aide du repository */
            transactionRepository.save(virement);
            return new ResponseEntity<>("Virement terminé. Le compte récepteur a été crédité", HttpStatus.OK);

        }
        return new ResponseEntity<>("Transaction refusée : La réponse fournie à la question secrète est incorrecte", HttpStatus.NOT_ACCEPTABLE);

    }
}
