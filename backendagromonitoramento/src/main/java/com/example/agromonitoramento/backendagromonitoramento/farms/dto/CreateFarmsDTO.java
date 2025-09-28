package com.example.agromonitoramento.backendagromonitoramento.farms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateFarmsDTO {

    @NotBlank(message = "Name farm is required." )
    private String name;

}
