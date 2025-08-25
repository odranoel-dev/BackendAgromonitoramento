package com.example.agromonitoramento.backendagromonitoramento.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;



@Data
@Entity
@DiscriminatorValue("BUSINESS")
public class UserBusinessModel extends UserModel{

    private String businessName;

    @Column(unique = true)
    private String cnpj;

}
