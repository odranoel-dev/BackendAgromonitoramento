package com.example.agromonitoramento.backendagromonitoramento.validations;

import com.example.agromonitoramento.backendagromonitoramento.users.UserBusinessRepository;
import com.example.agromonitoramento.backendagromonitoramento.users.UserIndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberValidationService {

    @Autowired
    private UserBusinessRepository userBusinessRepository;

    @Autowired
    private UserIndividualRepository userIndividualRepository;

    public void validatePhoneNumber(String phoneNumber) {

        if (phoneNumber.length() < 12) {
            throw new IllegalArgumentException("Phone number is too short.");
        }

        //Verifica se o telefone já existe no banco, consultando no repositório.

        if (userBusinessRepository.existsByPhoneNumber(phoneNumber)||
                userIndividualRepository.existsByPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("The phone number is already registered.");
        }

    }
}
