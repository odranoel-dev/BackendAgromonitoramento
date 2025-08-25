package com.example.agromonitoramento.backendagromonitoramento.service;

import com.example.agromonitoramento.backendagromonitoramento.dto.LoginUserDTO;
import com.example.agromonitoramento.backendagromonitoramento.errors.InvalidPasswordException;
import com.example.agromonitoramento.backendagromonitoramento.model.UserBusinessModel;
import com.example.agromonitoramento.backendagromonitoramento.model.UserIndividualModel;
import com.example.agromonitoramento.backendagromonitoramento.model.UserModel;
import com.example.agromonitoramento.backendagromonitoramento.repository.UserBusinessRepository;
import com.example.agromonitoramento.backendagromonitoramento.repository.UserIndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationUserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserIndividualRepository userIndividualRepository;

    @Autowired
    private UserBusinessRepository userBusinessRepository;

    public void identificationUser(LoginUserDTO loginUsuarioDTO) {

        String email = loginUsuarioDTO.getEmail();
        String password = loginUsuarioDTO.getPassword();

        //Obs: a verificação se os campos email e senha foram preenchidos é realizado no dto c/NotBlank

        Optional<UserIndividualModel> userOpt = userIndividualRepository.findByEmail(email);
        if(userOpt.isPresent()){
            authenticate(userOpt.get(), password, userIndividualRepository);
            return;
        }

        Optional<UserBusinessModel> userBusOpt = userBusinessRepository.findByEmail(email);
        if(userBusOpt.isPresent()){
            authenticate(userBusOpt.get(), password, userBusinessRepository);
            return;
        }

        throw new IllegalArgumentException("User not found");

    }


    private <T extends UserModel> void authenticate(
            T user, String password, JpaRepository<T, UUID> repository) {

        if (!user.isActive()) {
            throw new IllegalArgumentException("User is inactive");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            user.setAttemptsLogin(user.getAttemptsLogin() + 1);

            if (user.getAttemptsLogin() >= 3) {
                user.setActive(false);
                repository.save(user); // salva antes de lançar
                throw new IllegalArgumentException("User inactivated due to too many login attempts");
            }

            repository.save(user); // salva a tentativa
            throw new InvalidPasswordException("Incorrect email or password");
        }

        // Se acertou a senha, zera tentativas
        if (user.getAttemptsLogin() > 0) {
            user.setAttemptsLogin(0);
            repository.save(user);
        }
    }

}
