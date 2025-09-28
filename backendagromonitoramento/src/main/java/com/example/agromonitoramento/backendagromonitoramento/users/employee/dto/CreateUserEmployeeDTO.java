package com.example.agromonitoramento.backendagromonitoramento.users.employee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateUserEmployeeDTO {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Position is required.")
    private String position;

    @NotBlank(message = "Phone number is required.")
    private String phoneNumber;

    private UUID farmId;
}
