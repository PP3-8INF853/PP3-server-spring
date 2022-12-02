package com.example.accountservice;

import com.example.accountservice.entities.Compte;
import com.example.accountservice.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

/*    @Bean
    CommandLineRunner runner (  AccountRepository accountRepository ) {
        return args -> {
            Compte c1 = Compte.builder()
                    .numero("1234")
                    .solde(2500)
                    .build();
            Compte c2 = Compte.builder()
                    .numero("5678")
                    .solde(275)
                    .build();
            Compte c3 = Compte.builder()
                    .numero("91011")
                    .solde(35)
                    .build();

            accountRepository.saveAll(List.of(c1,c2,c3));
        };
    }*/
}
