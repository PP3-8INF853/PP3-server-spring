package com.example.authenticationservice.service;

import com.example.authenticationservice.entities.Compte;
import com.example.authenticationservice.repository.AccountRepository;
import com.example.authenticationservice.dto.CustomerLoginDTO;
import com.example.authenticationservice.dto.CustomerSignUpDTO;
import com.example.authenticationservice.entities.Customer;
import com.example.authenticationservice.repository.CustomerRepository;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CustomerService {
    CustomerRepository customerRepository;
    AccountRepository accountRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository){
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public List<Customer> findAll(){
        return this.customerRepository.findAll();
    }
    public Customer create(CustomerSignUpDTO customerSignUpDTO){
        Customer customer = new Customer(customerSignUpDTO.username, customerSignUpDTO.firstname,
                customerSignUpDTO.lastname, customerSignUpDTO.password, customerSignUpDTO.email, customerSignUpDTO.phoneNumber);

        customerRepository.save(customer);

        Compte compte = new Compte();
        compte.setSolde(0d);
        compte.setNumero(String.valueOf(ThreadLocalRandom.current().nextInt(1, 34234 + 1)));
        compte.setUserId(customer.id);

        accountRepository.saveAccount(compte);

        //Util.makeHttpPostRequest("http://localhost:4444/account-service/accounts", compte);

        return customer;
    }

    public String login(CustomerLoginDTO customerLoginDTO){
        Optional<Customer> found = customerRepository.findAll().stream().filter(custom ->
                Objects.equals(custom.username, customerLoginDTO.username) && Objects.equals(custom.password, customerLoginDTO.password)).findFirst();

        return found.map(customer -> customer.id).orElse(null);
    }
}
