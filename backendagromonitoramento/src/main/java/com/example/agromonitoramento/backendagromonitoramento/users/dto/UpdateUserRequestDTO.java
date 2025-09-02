package com.example.agromonitoramento.backendagromonitoramento.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserRequestDTO {

    private String name;

    @Email(message = "Email is invalid.")
    private String email;

    private String phoneNumber;
}
