package com.example.agromonitoramento.backendagromonitoramento.service;

import com.example.agromonitoramento.backendagromonitoramento.config.ModelMapperConfig;
import com.example.agromonitoramento.backendagromonitoramento.dto.RegisterUserIndividualDTO;
import com.example.agromonitoramento.backendagromonitoramento.dto.UpdateUserIndividualRequestDTO;
import com.example.agromonitoramento.backendagromonitoramento.dto.UpdateUserIndividualResponseDTO;
import com.example.agromonitoramento.backendagromonitoramento.dto.UpdateUserResponseDTO;
import com.example.agromonitoramento.backendagromonitoramento.model.UserIndividualModel;
import com.example.agromonitoramento.backendagromonitoramento.repository.UserIndividualRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserIndividualService {

    @Autowired
    private UserIndividualRepository userIndividualRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private WhatsappService whatsAppService;

    @Autowired
    private PasswordValidationService passwordValidationService;

    @Autowired
    private EmailValidationService emailValidationService;

    @Autowired
    private PhoneNumberValidationService phoneNumberValidationService;

    @Autowired
    private ModelMapper modelMapper;


    public void createUserIndividual(RegisterUserIndividualDTO dto) {

        String name = dto.getName();
        String cpf = dto.getCpf();
        String email = dto.getEmail();
        String password = dto.getPassword();
        String phoneNumber = dto.getPhoneNumber();

        validateCpf(cpf);
        passwordValidationService.passwordValidation(password);
        emailValidationService.emailValidation(email);
        phoneNumberValidationService.validatePhoneNumber(phoneNumber);

        UserIndividualModel userIndividualModel = new UserIndividualModel();

        //Criptografa a senha antes de salvar
        userIndividualModel.setPassword(passwordEncoder.encode(password));
        userIndividualModel.setCpf(cpf);
        userIndividualModel.setEmail(email);
        userIndividualModel.setName(name);
        userIndividualModel.setPhoneNumber(phoneNumber);
        userIndividualModel.setActive(true);

        userIndividualRepository.save(userIndividualModel);

        whatsAppService.enviarMensagemBoasVindas(name,phoneNumber);
    }

    public void validateCpf(String cpf) {

        //Verifica se CPF já existe no banco, consultando o repositório.

        if (userIndividualRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("CPF is already registered.");
        }

    }


    public UpdateUserIndividualResponseDTO updateUserIndividual(UUID id, UpdateUserIndividualRequestDTO updateUserIndividualRequestDTO){

        UserIndividualModel usuario = userIndividualRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(updateUserIndividualRequestDTO.getName()!= null){
            usuario.setName(updateUserIndividualRequestDTO.getName());
        }

        if (updateUserIndividualRequestDTO.getPhoneNumber() != null){
            phoneNumberValidationService.validatePhoneNumber(updateUserIndividualRequestDTO.getPhoneNumber());
            usuario.setPhoneNumber(updateUserIndividualRequestDTO.getPhoneNumber());
        }

        if (updateUserIndividualRequestDTO.getEmail() != null){
            emailValidationService.emailValidation(updateUserIndividualRequestDTO.getEmail());
            usuario.setEmail(updateUserIndividualRequestDTO.getEmail());
        }

        userIndividualRepository.save(usuario);

        return modelMapper.map(usuario,UpdateUserIndividualResponseDTO.class);
    }

}



