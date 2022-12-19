package com.example.transactionservice.service;

import com.example.transactionservice.entities.Virement;
import com.example.transactionservice.entities.VirementSendDTO;
import com.example.transactionservice.enums.StatutVirement;
import com.example.transactionservice.models.Compte;
import com.example.transactionservice.entities.VirementReceiveDTO;
import com.example.transactionservice.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VirementService {
    private CompteRestClient compteRestClient ;
    private TransactionRepository transactionRepository;

    public ResponseEntity<Object> sendMoney(VirementSendDTO virementSendDTO) {
        try {
            Compte compteRecepteur = compteRestClient.accountByNumero(virementSendDTO.getNumCompteRecepteur());
            Compte compteEmetteur = compteRestClient.accountByNumero(virementSendDTO.getNumCompteEmetteur());

            // Vérifie si le compte emetteur a un solde suffisant pour l'envoi
            if (compteEmetteur.getSolde()>=virementSendDTO.getMontant()) {

                // Modifie le solde du client emetteur
                compteEmetteur.setSolde(compteEmetteur.getSolde()-virementSendDTO.getMontant());
                compteRestClient.updateAccount(compteEmetteur.getId(), compteEmetteur);

                // Définit le virement à persister dans la bd
                Virement virement = Virement.builder()
                        .compteEmetteur(compteEmetteur)
                        .compteRecepteur(compteRecepteur)
                        .dateEnvoi(LocalDateTime.now())
                        .montant(virementSendDTO.getMontant())
                        .statut(StatutVirement.EN_COURS)
                        .question(virementSendDTO.getQuestion())
                        .reponse(virementSendDTO.getReponse())
                        .idCompteEmetteur(compteEmetteur.getId())
                        .idCompteRecepteur(compteRecepteur.getId())
                        .build();


                // Sauvegarde le virement dans la bd
                transactionRepository.save(virement);
                return new ResponseEntity<>(Map.of("message", "Virement effectué avec succès. En attente de la confirmation du récepteur"), HttpStatus.OK);
            }
/*            virement.setStatut(StatutVirement.ECHEC);
            transactionRepository.save(virement);*/
            return new ResponseEntity<>(Map.of("message", "Solde du compte émetteur insuffisant"), HttpStatus.NOT_ACCEPTABLE);

        }
        catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.FORBIDDEN);
        }
            }

    public ResponseEntity<String> receiveMoney(String idVirement, VirementReceiveDTO reponse) {
        // Récupère le virement dont l'id est passé en paramètre
        Optional<Virement> virementOptional = transactionRepository.findById(idVirement);

        // Retourne une réponse négative si le virement n'existe pas
        if (virementOptional.isEmpty())
            return new ResponseEntity<>("Le virement recherché est introuvable", HttpStatus.NOT_FOUND);
        Virement virement = virementOptional.get();
        // Vérifie si la transaction est toujours en cours. Dans le cas contraire ne pas créditer le compte
        if (!virement.getStatut().equals(StatutVirement.EN_COURS))
            return new ResponseEntity<>("Le virement mentionné a déjà été effectué ou annulé", HttpStatus.FORBIDDEN);

        // Vérifie si le numéro de compte passé dans le corps de la requête est bien celui du destinataire
        if (!reponse.getIdCompte().equals(virement.getIdCompteRecepteur()))
            return new ResponseEntity<>("Le numéro de compte fourni ne correspond pas au numéro de compte destinataire", HttpStatus.FORBIDDEN);

        try {
            Compte compteRecepteur = compteRestClient.accountById(virement.getIdCompteRecepteur());

            // Vérifie si la réponse à la question du virement est la bonne
            if (virement.getReponse().equals(reponse.getResponse())) {

                // Modifie le solde du client récepteur
                compteRecepteur.setSolde(compteRecepteur.getSolde() + virement.getMontant());
                compteRestClient.updateAccount(compteRecepteur.getId(), compteRecepteur);

                // Modifie le statut du virement et la date de recepetion
                virement.setStatut(StatutVirement.EFFECTUE);
                virement.setDateReception(LocalDateTime.now());

                // Enregistre le virement dans la bd
                transactionRepository.save(virement);
                return new ResponseEntity<>("Virement terminé. Le compte récepteur a été crédité", HttpStatus.OK);

            }
            return new ResponseEntity<>("Transaction refusée : La réponse fournie à la question secrète est incorrecte", HttpStatus.NOT_ACCEPTABLE);


        } catch (Exception e) {
            return new ResponseEntity<>("Le compte récepteur associé à ce virement est introuvable", HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<List<Virement>> findTransactionsByAccountId(String id) {
        return new ResponseEntity<>(transactionRepository.findAllByIdCompteEmetteurOrIdCompteRecepteur(id), HttpStatus.OK);
    }
}
