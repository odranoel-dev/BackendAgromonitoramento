package com.example.agromonitoramento.backendagromonitoramento.model;

import com.example.agromonitoramento.backendagromonitoramento.enums.GeneroUsuarioEnum;
import com.example.agromonitoramento.backendagromonitoramento.enums.StatusUsuarioEnum;
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

    @Enumerated(EnumType.STRING) //serve para indicar como um atributo enum deve ser salvo no banco de dados
    @Column(nullable = false,length = 10)
    private StatusUsuarioEnum statusDoUsuario;

    @Column(nullable = false, length = 150)
    private String nomeCompleto;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 10)
    private GeneroUsuarioEnum genero;

    @Column(length = 15)
    private String telefone1;

    @Column(length = 15)
    private String telefone2;

    @Column(length = 15)
    private String telefone3;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(length = 10)
    private int tentativasFalhasLogin = 0;

    @CreationTimestamp
    private LocalDateTime dataDoCadastro;


}
