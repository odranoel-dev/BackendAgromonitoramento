package com.example.agromonitoramento.backendagromonitoramento.repository;

import com.example.agromonitoramento.backendagromonitoramento.enums.StatusUsuarioEnum;
import com.example.agromonitoramento.backendagromonitoramento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,UUID> {

    Optional<Usuario>findByEmail(String email);
    Optional<Usuario>findByCpf(String cpf);
    Optional<Usuario>findByNomeCompletoContaining(String nome);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);


    boolean existsByTelefone1(String telefone1);
    boolean existsByTelefone2(String telefone2);
    boolean existsByTelefone3(String telefone3);


    }
