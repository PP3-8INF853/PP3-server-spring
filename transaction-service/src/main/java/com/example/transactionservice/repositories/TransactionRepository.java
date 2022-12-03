package com.example.transactionservice.repositories;

import com.example.transactionservice.entities.Virement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface TransactionRepository extends MongoRepository<Virement, String> {
    @Query("{$or :[{idCompteEmetteur: ?0},{idCompteRecepteur: ?0}]}")
    List<Virement> findAllByIdCompteEmetteurOrIdCompteRecepteur(String numCompte);
}
