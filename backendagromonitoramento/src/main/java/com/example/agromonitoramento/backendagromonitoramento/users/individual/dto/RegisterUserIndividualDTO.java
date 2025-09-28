package com.example.agromonitoramento.backendagromonitoramento.users.individual.dto;

import com.example.agromonitoramento.backendagromonitoramento.users.dto.RegisterUserDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class RegisterUserIndividualDTO extends RegisterUserDTO {

    @NotBlank(message = "CPF is required.")
    @CPF(message = "CPF is invalid.")
    private String cpf;

}
