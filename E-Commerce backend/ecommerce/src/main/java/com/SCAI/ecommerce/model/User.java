package com.SCAI.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Classe Modello, contiene proprieta e metodi, rappresenta la struttura dei dati dell'applicazione
@Getter
@Setter
@Entity // Mappa la classe a una tabella di un db, richiede @Id
public class User {

    public enum Role {
        ADMIN,
        GUEST
    }

    @Id // specifica l'attributo PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String password;
    @Column(unique = true)
    private String email;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Role role;
}
