package com.example.agromonitoramento.backendagromonitoramento.farms;

import com.example.agromonitoramento.backendagromonitoramento.users.business.UserBusinessModel;
import com.example.agromonitoramento.backendagromonitoramento.users.employee.UserEmployeeModel;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class FarmsModel {


    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(SqlTypes.UUID) //garante que o tipo no banco será uuid
    @Column(columnDefinition = "uuid", updatable = false,nullable = false) //Define no banco a coluna com tipo de UUID
    private UUID id;

    private String name;

    private Boolean isActive = true;
    // uma fazenda pertence a um UserBusiness
    // um UserBusiness pode ter varias fazendas

    @ManyToOne
    @JoinColumn(name = "user_business_id")
    private UserBusinessModel userBusiness;

    @OneToMany(mappedBy = "farm")
    private List<UserEmployeeModel> employee;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
