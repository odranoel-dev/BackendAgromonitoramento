package com.example.agromonitoramento.backendagromonitoramento.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class RegisterUserIndividualDTO {
    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "CPF is required.")
    @CPF(message = "CPF is invalid.")// se usar Hibernate Validator (valida formato e dígito)
    private String cpf;

    @NotBlank(message = "Email is required.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    private int phoneNumber;

    @NotBlank(message = "Password is required.")
    private String password;
}
