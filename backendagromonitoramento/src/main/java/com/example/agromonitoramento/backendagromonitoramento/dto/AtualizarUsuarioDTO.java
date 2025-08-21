package com.example.agromonitoramento.backendagromonitoramento.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class AtualizarUsuarioDTO {

    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String telefone1;
    private String telefone2;
    private String telefone3;

}
