package com.example.agromonitoramento.backendagromonitoramento.controller;

import com.example.agromonitoramento.backendagromonitoramento.dto.RegisterUserBusinessDTO;
import com.example.agromonitoramento.backendagromonitoramento.dto.RegisterUserIndividualDTO;
import com.example.agromonitoramento.backendagromonitoramento.dto.LoginUserDTO;
import com.example.agromonitoramento.backendagromonitoramento.service.AuthenticationUserService;
import com.example.agromonitoramento.backendagromonitoramento.service.UserBusinessService;
import com.example.agromonitoramento.backendagromonitoramento.service.UserIndividualService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Tag(name="Users")
public class UsersController {

    @Autowired
    private UserIndividualService userIndividualService;

    @Autowired
    private AuthenticationUserService authenticationUserService;

    @Autowired
    private UserBusinessService userBusinessService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginUserDTO loginUsuarioDTO){
        this.authenticationUserService.identificationUser(loginUsuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body("User logged in!");
    }

    @PostMapping("/register-individual")
    public ResponseEntity<String> registerIndividual(@RequestBody @Valid RegisterUserIndividualDTO registerUserIndividualDTO) {
        this.userIndividualService.createUserIndividual(registerUserIndividualDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    @PostMapping("/register-business")
    public ResponseEntity<String> registerBusiness(@RequestBody @Valid RegisterUserBusinessDTO registerUserBusinessDTO) {
        this.userBusinessService.createUserBusiness(registerUserBusinessDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    /*
    // TODO: id será pego pelo token de autenticação
    @PutMapping("/update-individual/{id}")
    public ResponseEntity<String> updateIndividual(
            @PathVariable UUID id,
            @RequestBody UpdateUserIndividualDTO atualizarUsuarioDTO
    ){
        this.userService.atualizarUsuario(id,atualizarUsuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body("User updated!");
    }


    // TODO: id será pego pelo token de autenticação
    @PutMapping("/update-business/{id}")
    public ResponseEntity<String> updateBusiness(
            @PathVariable UUID id,
            @RequestBody UpdateUserIndividualDTO atualizarUsuarioDTO
    ){
        this.userService.atualizarUsuario(id,atualizarUsuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body("User updated!");
    }

     */
}
