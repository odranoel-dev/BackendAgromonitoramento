package com.example.agromonitoramento.backendagromonitoramento.errors;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException(String message) {
        super(message);
    }
}
