package com.example.agromonitoramento.backendagromonitoramento.errors;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {

    private int status;
    private String erro;
    private List<FieldErrorResponse> erros;

    public ErrorResponse(int status, String erro, List<FieldErrorResponse> erros){
        this.status = status;
        this.erro = erro;
        this.erros = erros;
    }

    @Data
    public static class FieldErrorResponse {
        private String campo;
        private String mensagem;

        public FieldErrorResponse(String campo, String mensagem){
            this.campo = campo;
            this.mensagem = mensagem;
        }
    }
}
