package com.example.accountservice.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Compte {

    @Id
    private String id;
    @Indexed(unique=true)
    private String numero;
    private double solde;
}
