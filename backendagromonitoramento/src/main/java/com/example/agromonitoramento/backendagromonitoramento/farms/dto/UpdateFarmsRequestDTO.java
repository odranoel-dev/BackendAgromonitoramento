package com.example.agromonitoramento.backendagromonitoramento.farms.dto;

import lombok.Data;

@Data
public class UpdateFarmsRequestDTO {

    private String name;

    private Boolean isActive;
}
