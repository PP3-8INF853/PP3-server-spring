package com.example.statementservice.entities;

import com.example.statementservice.models.Compte;
import com.example.statementservice.models.Virement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ReleveBancaire {
    private LocalDateTime dateReleve;
    private Compte compteClient;
    private List<Virement> listVirements;

}
