package com.example.agromonitoramento.backendagromonitoramento.validations;

import com.example.agromonitoramento.backendagromonitoramento.users.dto.LoginUserRequestDTO;
import com.example.agromonitoramento.backendagromonitoramento.errors.InvalidPasswordException;
import com.example.agromonitoramento.backendagromonitoramento.users.UserBusinessModel;
import com.example.agromonitoramento.backendagromonitoramento.users.UserIndividualModel;
import com.example.agromonitoramento.backendagromonitoramento.users.UserModel;
import com.example.agromonitoramento.backendagromonitoramento.users.UserBusinessRepository;
import com.example.agromonitoramento.backendagromonitoramento.users.UserIndividualRepository;
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

    public UserModel identificationUser(LoginUserRequestDTO loginUsuarioDTO) {

        String email = loginUsuarioDTO.getEmail();
        String password = loginUsuarioDTO.getPassword();

        Optional<UserIndividualModel> userOpt = userIndividualRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            return authenticate(userOpt.get(), password, userIndividualRepository);
        }

        Optional<UserBusinessModel> userBusOpt = userBusinessRepository.findByEmail(email);
        if (userBusOpt.isPresent()) {
            return authenticate(userBusOpt.get(), password, userBusinessRepository);
        }

        throw new IllegalArgumentException("User not found");
    }

    private <T extends UserModel> T authenticate(
            T user, String password, JpaRepository<T, UUID> repository) {

        if (!user.isActive()) {
            throw new IllegalArgumentException("User is inactive");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            user.setAttemptsLogin(user.getAttemptsLogin() + 1);

            if (user.getAttemptsLogin() >= 3) {
                user.setActive(false);
                repository.save(user);
                throw new IllegalArgumentException("User inactivated due to too many login attempts");
            }

            repository.save(user);
            throw new InvalidPasswordException("Incorrect email or password");
        }

        // Zera tentativas se senha correta
        if (user.getAttemptsLogin() > 0) {
            user.setAttemptsLogin(0);
            repository.save(user);
        }

        return user;
    }
}
