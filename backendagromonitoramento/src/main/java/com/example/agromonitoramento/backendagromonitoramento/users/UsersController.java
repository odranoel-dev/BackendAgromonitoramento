package com.example.agromonitoramento.backendagromonitoramento.users;

import com.example.agromonitoramento.backendagromonitoramento.jwt.JwtService;
import com.example.agromonitoramento.backendagromonitoramento.users.business.UserBusinessService;
import com.example.agromonitoramento.backendagromonitoramento.users.business.dto.RegisterUserBusinessDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.business.dto.UpdateUserBusinessRequestDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.business.dto.UpdateUserBusinessResponseDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.individual.UserIndividualService;
import com.example.agromonitoramento.backendagromonitoramento.users.individual.dto.RegisterUserIndividualDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.individual.dto.UpdateUserIndividualRequestDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.individual.dto.UpdateUserIndividualResponseDTO;
import com.example.agromonitoramento.backendagromonitoramento.validations.AuthenticationUserService;
import com.example.agromonitoramento.backendagromonitoramento.users.dto.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDTO> login(
            @RequestBody LoginUserRequestDTO loginUserRequestDTO,
            HttpServletResponse response) {

        UserModel user = authenticationUserService.identificationUser(loginUserRequestDTO);

        long duracao = 300L; // 5 minutos
        String token = jwtService.gerarToken(user.getId().toString(), duracao);
        Cookie cookie = jwtService.gerarCookie(token, duracao);

        response.addCookie(cookie);

        return ResponseEntity.ok(new LoginUserResponseDTO(null, duracao));
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
