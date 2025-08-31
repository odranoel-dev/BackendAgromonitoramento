package com.example.agromonitoramento.backendagromonitoramento.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserBusinessRequestDTO extends UpdateUserRequestDTO {

    private String businessName;

}
