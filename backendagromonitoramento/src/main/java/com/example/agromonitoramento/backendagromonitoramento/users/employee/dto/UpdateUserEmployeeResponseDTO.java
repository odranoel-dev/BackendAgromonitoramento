package com.example.agromonitoramento.backendagromonitoramento.users.employee.dto;

import lombok.Data;

@Data
public class UpdateUserEmployeeResponseDTO {

    private String name;
    private String position;
    private boolean isActive;
    private String farmName;
}
