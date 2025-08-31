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

        //isEmpty()	A string não tem nenhum caractere
        //isBlank()	A string está vazia ou só tem espaços

        boolean isEmailEmpty = email == null || email.isBlank();

        if(isEmailEmpty){
            throw new IllegalArgumentException("Email is required");
        }


        //Verifica se o email já existe no banco, consultando no repositório.
        if (userBusinessRepository.existsByEmail(email)||userIndividualRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("The email is already registered.");
        }

    }
}
