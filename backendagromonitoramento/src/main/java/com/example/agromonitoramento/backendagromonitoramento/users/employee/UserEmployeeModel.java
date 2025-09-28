package com.example.agromonitoramento.backendagromonitoramento.users.employee;

import com.example.agromonitoramento.backendagromonitoramento.farms.FarmsModel;
import com.example.agromonitoramento.backendagromonitoramento.users.business.UserBusinessModel;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class UserEmployeeModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(columnDefinition = "uuid", updatable = false,nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "farms_id")
    private FarmsModel farm;

    private boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;




        /*

     #ele vai ter somente uma fazenda !
     #as fazendas que tem funcionário.

    uma fazenda pode ter varios employee, mas um employee pode ter somente uma fazenda.

    Muitos para um.

    Quem vai ter a lista ? Fazenda


    private List<UserEmployeeModel> employee;

     */
}
