package com.example.agromonitoramento.backendagromonitoramento.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;



@Data
@Entity
@DiscriminatorValue("BUSINESS")
public class UserBusinessModel extends UserModel{

    @Column(nullable = false)
    private String businessName;

    @Column(unique = true, nullable = false)
    private String cnpj;

}
