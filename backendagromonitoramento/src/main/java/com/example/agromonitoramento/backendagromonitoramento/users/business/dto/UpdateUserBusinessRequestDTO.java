package com.example.agromonitoramento.backendagromonitoramento.users.business.dto;

import com.example.agromonitoramento.backendagromonitoramento.users.dto.UpdateUserRequestDTO;
import lombok.Data;

@Data
public class UpdateUserBusinessRequestDTO extends UpdateUserRequestDTO {

    private String businessName;

}
