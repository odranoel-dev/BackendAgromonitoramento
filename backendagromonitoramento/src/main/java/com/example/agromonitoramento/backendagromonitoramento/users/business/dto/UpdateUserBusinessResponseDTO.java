package com.example.agromonitoramento.backendagromonitoramento.users.business.dto;

import com.example.agromonitoramento.backendagromonitoramento.users.dto.UpdateUserResponseDTO;
import lombok.Data;

@Data
public class UpdateUserBusinessResponseDTO extends UpdateUserResponseDTO {
    private String businessName;
}
