package com.example.agromonitoramento.backendagromonitoramento.farms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateFarmsResponseDTO {

    private String name;
    private boolean isActive;
}
