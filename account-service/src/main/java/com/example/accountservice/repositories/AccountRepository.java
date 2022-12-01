package com.example.accountservice.repositories;

import com.example.accountservice.entities.Compte;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountRepository extends MongoRepository<Compte, Long> {
}
