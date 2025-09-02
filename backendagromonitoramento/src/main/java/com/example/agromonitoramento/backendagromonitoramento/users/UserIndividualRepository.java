package com.example.agromonitoramento.backendagromonitoramento.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserIndividualRepository extends JpaRepository<UserIndividualModel,UUID> {

    Optional<UserIndividualModel>findByEmail(String email);
    Optional<UserIndividualModel>findByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByPhoneNumber(String phoneNumber);

    }
