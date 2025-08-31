package com.example.agromonitoramento.backendagromonitoramento.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateUserResponseDTO {

   private UUID id;
   private String name;
   private String email;
   private String phoneNumber;

}
