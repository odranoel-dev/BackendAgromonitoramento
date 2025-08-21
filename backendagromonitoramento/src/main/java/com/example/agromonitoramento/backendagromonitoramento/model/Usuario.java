package com.example.agromonitoramento.backendagromonitoramento.model;

import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "uuid") //Define no banco a coluna com tipo de UUID
    private UUID id;

    @Column(nullable = false)
    private Boolean statusDoUsuario;

    @Column(nullable = false, length = 150)
    private String nomeCompleto;

    @Column(nullable = false)
    private LocalDate dataNascimento;//remover

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(length = 15)
    private String telefone1;

    @Column(length = 15)
    private String telefone2;//remover

    @Column(length = 15)
    private String telefone3;//remover

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(length = 10)
    private int tentativasFalhasLogin = 0;

    @CreationTimestamp
    private LocalDateTime dataDoCadastro;
}
