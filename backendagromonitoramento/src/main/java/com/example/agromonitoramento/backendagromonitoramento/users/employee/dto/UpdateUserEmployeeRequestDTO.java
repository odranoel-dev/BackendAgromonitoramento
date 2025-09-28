package com.example.agromonitoramento.backendagromonitoramento.users.employee.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateUserEmployeeRequestDTO {

    private String name;
    private String position;
    private Boolean isActive;
    private UUID farmId;


}
