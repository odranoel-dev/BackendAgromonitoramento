package com.example.agromonitoramento.backendagromonitoramento.users.employee;

import com.example.agromonitoramento.backendagromonitoramento.users.employee.dto.CreateUserEmployeeDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.employee.dto.ListUserEmployeeDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.employee.dto.UpdateUserEmployeeRequestDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.employee.dto.UpdateUserEmployeeResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/userEmployee")
@RestController
@Tag(name = "Employee")
public class UserEmployeeController {

    @Autowired
    private UserEmployeeService userEmployeeService;

    @PostMapping("/create")
    public ResponseEntity<String>createEmployee(@RequestBody @Valid CreateUserEmployeeDTO createUserEmployeeDTO){

        this.userEmployeeService.createdEmployee(createUserEmployeeDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateUserEmployeeResponseDTO> updateEmployee(@RequestBody @Valid UpdateUserEmployeeRequestDTO updateUserEmployeeRequestDTO,
                                                                        @PathVariable UUID id){

        UpdateUserEmployeeResponseDTO employeeResponseDTO =
                userEmployeeService.updateEmployee(id,updateUserEmployeeRequestDTO);

        return ResponseEntity.ok(employeeResponseDTO);
    }

    @GetMapping("/{farmId}")
    public List<ListUserEmployeeDTO> getEmployeesByFarmId(@PathVariable UUID farmId){

        return this.userEmployeeService.getEmployeesByFarmId(farmId);

    }
}
