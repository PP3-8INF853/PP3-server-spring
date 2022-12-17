package com.example.accountservice.repositories;

import com.example.accountservice.entities.Compte;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Map;
import java.util.Optional;

@RepositoryRestResource
public interface AccountRepository extends MongoRepository<Compte, String> {
    @Override
    Optional<Compte> findById(String s);
    Optional<Compte> findByNumero(String numero);
}
