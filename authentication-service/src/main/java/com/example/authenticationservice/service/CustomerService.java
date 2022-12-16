package com.example.authenticationservice.service;

import com.example.authenticationservice.dto.CustomerLoginDTO;
import com.example.authenticationservice.dto.CustomerSignUpDTO;
import com.example.authenticationservice.entities.Customer;
import com.example.authenticationservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    public CustomerService(){

    }

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll(){
        return this.customerRepository.findAll();
    }
    public Customer create(CustomerSignUpDTO customerSignUpDTO){
        Customer customer = new Customer(customerSignUpDTO.username, customerSignUpDTO.firstname,
                customerSignUpDTO.lastname, customerSignUpDTO.password, customerSignUpDTO.email, customerSignUpDTO.phoneNumber);

        customerRepository.save(customer);
        return customer;
    }

    public String login(CustomerLoginDTO customerLoginDTO){
        Optional<Customer> found = customerRepository.findAll().stream().filter(custom ->
                Objects.equals(custom.username, customerLoginDTO.username) && Objects.equals(custom.password, customerLoginDTO.password)).findFirst();

        return found.map(customer -> customer.id).orElse(null);
    }
}
