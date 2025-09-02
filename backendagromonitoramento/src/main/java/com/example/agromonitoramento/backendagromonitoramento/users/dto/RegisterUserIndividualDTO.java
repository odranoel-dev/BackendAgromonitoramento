package com.example.agromonitoramento.backendagromonitoramento.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class RegisterUserIndividualDTO extends RegisterUserDTO{

    @NotBlank(message = "CPF is required.")
    @CPF(message = "CPF is invalid.")// se usar Hibernate Validator (valida formato e dígito)
    private String cpf;

}
