package com.example.agromonitoramento.backendagromonitoramento.dto;

public class WhatsappDTO {

    private String number;
    private String text;

    public WhatsappDTO(String number, String text) {
        this.number = number;
        this.text = text;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
