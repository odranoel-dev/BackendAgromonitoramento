package com.example.agromonitoramento.backendagromonitoramento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class UserBusinessModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "uuid") //Define no banco a coluna com tipo de UUID
    private UUID id;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false)
    private String ownerName;

    @Column(length = 13)
    private int ownerPhoneNumber;

    @Column(nullable = false, unique = true)
    private String ownerEmail;

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
