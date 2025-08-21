package com.example.agromonitoramento.backendagromonitoramento.dto;

import lombok.Data;

@Data
public class UpdateUserBusinessDTO {
    private String businessName;
    private String ownerName;
    private String ownerEmail;
    private int ownerPhoneNumber;
}
