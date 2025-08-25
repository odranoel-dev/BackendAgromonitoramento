package com.example.agromonitoramento.backendagromonitoramento.dto;

import lombok.Data;

@Data
public class WhatsappDTO {

    private String number;
    private String text;

    public WhatsappDTO(String number, String text) {
        this.number = number;
        this.text = text;
    }
}
