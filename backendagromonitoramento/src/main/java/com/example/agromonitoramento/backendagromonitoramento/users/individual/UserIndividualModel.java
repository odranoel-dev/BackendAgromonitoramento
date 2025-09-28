package com.example.agromonitoramento.backendagromonitoramento.users.individual;

import com.example.agromonitoramento.backendagromonitoramento.users.UserModel;
import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@DiscriminatorValue("INDIVIDUAL")
public class UserIndividualModel extends UserModel {

    @Column(unique = true)
    private String cpf;

}
