package com.example.agromonitoramento.backendagromonitoramento.validations;

import com.example.agromonitoramento.backendagromonitoramento.errors.InvalidPasswordException;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidationService {

    public void passwordValidation(String password) {

        //validar senha (Total 8 caracteres, sendo maiusculo, minusculo, especial e numerico)

        if (password == null || password.length() < 8) {
            throw new InvalidPasswordException("Password must contain at least 8 characters.");
        }

        if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*")) {
            throw new InvalidPasswordException("The password must contain uppercase and lowercase letters.");
        }

        if (!password.matches(".*[0-9].*")) {
            throw new InvalidPasswordException("Password must contain at least one number.");
        }

        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            throw new InvalidPasswordException("The password must contain at least one special character.");
        }
    }
}
