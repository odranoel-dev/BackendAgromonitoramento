package com.example.agromonitoramento.backendagromonitoramento.service;

import com.example.agromonitoramento.backendagromonitoramento.repository.UserBusinessRepository;
import com.example.agromonitoramento.backendagromonitoramento.repository.UserIndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailValidationService {

    @Autowired
    private UserBusinessRepository userBusinessRepository;

    @Autowired
    private UserIndividualRepository userIndividualRepository;

    public void emailValidation(String email) {

        //Verifica se o email já existe no banco, consultando no repositório.

        if (userBusinessRepository.existsByEmail(email)||userIndividualRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("The email is already registered.");
        }

    }
}
