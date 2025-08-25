package com.example.agromonitoramento.backendagromonitoramento.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
public class RegisterUserBusinessDTO extends RegisterUserDTO{

    @NotBlank(message = "Business name is required.")
    private String businessName;

    @NotBlank(message = "CNPJ is required.")
    @CNPJ(message = "CNPJ is invalid.")// se usar Hibernate Validator (valida formato e dígito)
    private String cnpj;

}
