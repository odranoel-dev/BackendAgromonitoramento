package com.example.agromonitoramento.backendagromonitoramento.controller;

import com.example.agromonitoramento.backendagromonitoramento.dto.*;
import com.example.agromonitoramento.backendagromonitoramento.model.UserModel;
import com.example.agromonitoramento.backendagromonitoramento.service.AuthenticationUserService;
import com.example.agromonitoramento.backendagromonitoramento.service.UserBusinessService;
import com.example.agromonitoramento.backendagromonitoramento.service.UserIndividualService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;


@RestController
@RequestMapping("/user")
@Tag(name="Users")
public class UsersController {

    @Autowired
    private AuthenticationUserService authenticationUserService;

    @Autowired
    private UserIndividualService userIndividualService;

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private JwtEncoder jwtEncoder;


    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDTO> loginResponseResponseEntity(
            @RequestBody LoginUserRequestDTO loginUserRequestDTO){

        UserModel user = this.authenticationUserService.identificationUser(loginUserRequestDTO);

        var now = Instant.now();
        var expiresIn = 300L;

        var claims = JwtClaimsSet.builder()
                .issuer("backendAgromonitoramento")
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginUserResponseDTO(jwtValue, expiresIn));

    }

    // passar login tipo request e retorno do tipo response.

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

    @PutMapping("/update-individual")
    public ResponseEntity<UpdateUserIndividualResponseDTO>updateIndividual(JwtAuthenticationToken token,
                                                                 @RequestBody @Valid UpdateUserIndividualRequestDTO updateUserIndividualRequestDTO) {

        UpdateUserIndividualResponseDTO user = userIndividualService.updateUserIndividual(
                UUID.fromString(token.getName()),
                updateUserIndividualRequestDTO);

        return ResponseEntity.ok(user);
    }


    @PutMapping("/update-business")
    public ResponseEntity<UpdateUserBusinessResponseDTO>updateBusiness(JwtAuthenticationToken token,
                                                                 @RequestBody @Valid UpdateUserBusinessRequestDTO updateUserBusinessRequestDTO) {

        UpdateUserBusinessResponseDTO user = userBusinessService.updateUserBusiness(
                UUID.fromString(token.getName()),
                updateUserBusinessRequestDTO);

        return ResponseEntity.ok(user);
    }
}
