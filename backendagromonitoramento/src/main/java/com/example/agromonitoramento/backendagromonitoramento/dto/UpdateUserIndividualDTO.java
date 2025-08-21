package com.example.agromonitoramento.backendagromonitoramento.dto;

import lombok.Data;

@Data
public class UpdateUserIndividualDTO {
    private String name;
    private String email;
    private int phoneNumber;
}
