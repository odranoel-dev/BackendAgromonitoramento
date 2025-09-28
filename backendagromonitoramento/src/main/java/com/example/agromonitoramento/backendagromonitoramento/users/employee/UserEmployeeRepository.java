package com.example.agromonitoramento.backendagromonitoramento.users.employee;

import com.example.agromonitoramento.backendagromonitoramento.users.employee.dto.ListUserEmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.UUID;

@Repository
public interface UserEmployeeRepository extends JpaRepository<UserEmployeeModel, UUID> {

    List<ListUserEmployeeDTO>findAllByFarmId(UUID id);
    boolean existsByPhoneNumber(String phoneNumbers);

}
