package com.example.agromonitoramento.backendagromonitoramento.service;

import com.example.agromonitoramento.backendagromonitoramento.dto.RegisterUserIndividualDTO;
import com.example.agromonitoramento.backendagromonitoramento.model.UserIndividualModel;
import com.example.agromonitoramento.backendagromonitoramento.repository.UserIndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

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

        //whatsAppService.enviarMensagensBoasVindasComPagamento(name,phoneNumber.get(0));
    }

    public void validateCpf(String cpf) {

        //Verifica se CPF já existe no banco, consultando o repositório.

        if (userIndividualRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("CPF is already registered.");
        }

    }


    /*
    public void UpdateUserIndividual(UUID id, UpdateUserIndividualDTO updateUserIndividualDTO){


        UserIndividualModel usuario = userIndividualRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));


        if(updateUserIndividualDTO.getName()!= null){
            usuario.setName(updateUserIndividualDTO.getName());
        }

        if (updateUserIndividualDTO.getDataNascimento()!=null){
            validarDataNascimento(updateUserIndividualDTO.getDataNascimento());
            usuario.setDataNascimento(updateUserIndividualDTO.getDataNascimento());
        }


        if (updateUserIndividualDTO.getPhoneNumber() !=) {

            if (atualizarUsuarioDTO.getTelefone1() !=null){
                usuario.setTelefone1(atualizarUsuarioDTO.getTelefone1());
            }
            validarTelefones(usuario.getTelefone1());
        }

        userBusinessRepository.save(usuario);

    }

     */
}



