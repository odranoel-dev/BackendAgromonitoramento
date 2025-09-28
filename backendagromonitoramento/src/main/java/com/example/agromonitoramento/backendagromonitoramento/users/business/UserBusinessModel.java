package com.example.agromonitoramento.backendagromonitoramento.users.business;

import com.example.agromonitoramento.backendagromonitoramento.farms.FarmsModel;
import com.example.agromonitoramento.backendagromonitoramento.users.UserModel;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;


@Data
@Entity
@DiscriminatorValue("BUSINESS")
public class UserBusinessModel extends UserModel {

    private String businessName;

    @Column(unique = true)
    private String cnpj;

    @OneToMany(mappedBy = "userBusiness")
    private List <FarmsModel> farms;

}
