package com.example.agromonitoramento.backendagromonitoramento.dto;

import lombok.Data;

@Data
public class

LoginUserResponseDTO {

    String acessToken;
    Long expiresIn;

    public LoginUserResponseDTO(String acessToken, Long expiresIn) {
        this.acessToken = acessToken;
        this.expiresIn = expiresIn;
    }
}
