package com.example.agromonitoramento.backendagromonitoramento.farms;

import com.example.agromonitoramento.backendagromonitoramento.config.CurrentUser;
import com.example.agromonitoramento.backendagromonitoramento.config.SecurityConfig;
import com.example.agromonitoramento.backendagromonitoramento.farms.dto.CreateFarmsDTO;
import com.example.agromonitoramento.backendagromonitoramento.farms.dto.ListFarmsResponseDTO;
import com.example.agromonitoramento.backendagromonitoramento.farms.dto.UpdateFarmsRequestDTO;
import com.example.agromonitoramento.backendagromonitoramento.farms.dto.UpdateFarmsResponseDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.business.UserBusinessModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/farms")
@Tag(name = "Farms")
public class FarmsController {

    private final FarmsService farmsService;

    public FarmsController(FarmsService farmsService) {
        this.farmsService = farmsService;
    }

    @PostMapping("/create")
    public ResponseEntity<String>createFarms(@RequestBody @Valid CreateFarmsDTO createFarmsDTO,
                                             @CurrentUser UserBusinessModel currentUser){

        this.farmsService.createFarms(createFarmsDTO,currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("Farm create successfully.");
    }

    @GetMapping("/list")
    public List<ListFarmsResponseDTO> listFarmsByUser(@CurrentUser UserBusinessModel currentUser) {
        return farmsService.listFarmsByUser(currentUser.getId());
    }


    @PutMapping("/update/{farmId}")
    public ResponseEntity<UpdateFarmsResponseDTO> updateFarms(
            @PathVariable UUID farmId,
            @RequestBody @Valid UpdateFarmsRequestDTO updateFarmsRequestDTO,
            @CurrentUser UserBusinessModel currentUser) {  // pega o usuário logado

        UpdateFarmsResponseDTO farmsResponse = farmsService.updateFarms(updateFarmsRequestDTO, farmId, currentUser);

        return ResponseEntity.ok(farmsResponse);
    }
}
