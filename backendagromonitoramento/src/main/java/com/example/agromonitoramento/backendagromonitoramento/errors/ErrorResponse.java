package com.example.agromonitoramento.backendagromonitoramento.errors;

import lombok.Data;

@Data
public class ErrorResponse {

    private int status;
    private String erro;
    private String mensagem;

    public ErrorResponse(int status, String erro, String mensagem){
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
    }

}
