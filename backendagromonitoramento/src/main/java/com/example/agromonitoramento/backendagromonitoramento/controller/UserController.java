package com.example.agromonitoramento.backendagromonitoramento.controller;

import com.example.agromonitoramento.backendagromonitoramento.dto.UpdateUserIndividualDTO;
import com.example.agromonitoramento.backendagromonitoramento.dto.RegisterUserBusinessDTO;
import com.example.agromonitoramento.backendagromonitoramento.dto.LoginUserDTO;
import com.example.agromonitoramento.backendagromonitoramento.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Tag(name="Users")
public class UserController {
    @Autowired
    private UsuarioService userService;

    @PostMapping("/register-individual")
    public ResponseEntity<String> registerIndividual(@RequestBody @Valid RegisterUserBusinessDTO cadastroUsuarioDTO) {
        this.userService.cadastrarUsuario(cadastroUsuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    @PostMapping("/register-business")
    public ResponseEntity<String> registerBusiness() {
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginUserDTO loginUsuarioDTO){
        this.userService.autenticarLogin(loginUsuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body("User logged in!");
    }

    // TODO: id será pego pelo token de autenticação
    @PutMapping("/update-individual/{id}")
    public ResponseEntity<String> updateIndividual(
            @PathVariable UUID id,
            @RequestBody UpdateUserIndividualDTO atualizarUsuarioDTO
    ){
        this.userService.atualizarUsuario(id,atualizarUsuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body("User updated!");
    }

    @PutMapping("/update-business/{id}")
    public ResponseEntity<String> updateBusiness(
            @PathVariable UUID id,
            @RequestBody UpdateUserIndividualDTO atualizarUsuarioDTO
    ){
        this.userService.atualizarUsuario(id,atualizarUsuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body("User updated!");
    }
}
