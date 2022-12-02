package com.example.transactionservice.repositories;

import com.example.transactionservice.entities.Virement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface TransactionRepository extends MongoRepository<Virement, String> {
}
