package com.example.authenticationservice.repository;

import com.example.authenticationservice.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface CustomerRepository extends MongoRepository<Customer, String> {
}
