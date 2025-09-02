package com.example.agromonitoramento.backendagromonitoramento.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserBusinessRepository extends JpaRepository<UserBusinessModel,UUID> {

    Optional<UserBusinessModel>findByEmail(String email);
    Optional<UserBusinessModel>findByCnpj(String cnpj);
    boolean existsByEmail(String email);
    boolean existsByCnpj(String cnpj);
    boolean existsByPhoneNumber(String phoneNumbers);

    }
