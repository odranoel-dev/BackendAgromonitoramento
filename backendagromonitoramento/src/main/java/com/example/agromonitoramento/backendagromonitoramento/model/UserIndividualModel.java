package com.example.agromonitoramento.backendagromonitoramento.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class UserIndividualModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "uuid") //Define no banco a coluna com tipo de UUID
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(length = 13)
    private int phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean isActive;

    @Column(columnDefinition = "int default 0")
    private int attemptsLogin;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
