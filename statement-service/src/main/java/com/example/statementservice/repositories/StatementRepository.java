package com.example.statementservice.repositories;

import com.example.statementservice.entities.ReleveBancaire;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StatementRepository extends MongoRepository<ReleveBancaire, String> {
}
