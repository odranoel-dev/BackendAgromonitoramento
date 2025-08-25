package com.example.agromonitoramento.backendagromonitoramento.service;

import com.example.agromonitoramento.backendagromonitoramento.dto.RegisterUserBusinessDTO;
import com.example.agromonitoramento.backendagromonitoramento.model.UserBusinessModel;
import com.example.agromonitoramento.backendagromonitoramento.model.UserIndividualModel;
import com.example.agromonitoramento.backendagromonitoramento.repository.UserBusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;



@Service
public class UserBusinessService {

    @Autowired
    private UserBusinessRepository userBusinessRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private WhatsappService whatsAppService;

    @Autowired
    private PasswordValidationService passwordValidationService;

    @Autowired
    private PhoneNumberValidationService phoneNumberValidationService;

    @Autowired
    private EmailValidationService emailValidationService;

    public void createUserBusiness(RegisterUserBusinessDTO registerUserBusinessDTO) {

        //validar businessName
        String businessName = registerUserBusinessDTO.getBusinessName();
        String cnpj = registerUserBusinessDTO.getCnpj();
        String ownerName = registerUserBusinessDTO.getName();
        String phoneNumber = registerUserBusinessDTO.getPhoneNumber();

        String ownerEmail = registerUserBusinessDTO.getEmail();

        String password = registerUserBusinessDTO.getPassword();


        passwordValidationService.passwordValidation(password);

        phoneNumberValidationService.validatePhoneNumber(phoneNumber);

        emailValidationService.emailValidation(ownerEmail);


        UserBusinessModel userBusinessModel = new UserBusinessModel();

        //Criptografa a senha antes de salvar
        userBusinessModel.setPassword(passwordEncoder.encode(password));
        userBusinessModel.setCnpj(cnpj);
        userBusinessModel.setEmail(ownerEmail);
        userBusinessModel.setName(ownerName);
        userBusinessModel.setPhoneNumber(phoneNumber);
        userBusinessModel.setActive(true);

        userBusinessRepository.save(userBusinessModel);
        //whatsAppService.enviarMensagensBoasVindasComPagamento(ownerName,ownerPhoneNumber.get(0));
    }

    public void validateCnpj(String cnpj) {

        //Verifica se cnpj já existe no banco, consultando no repositório.

        if (userBusinessRepository.existsByCnpj(cnpj)) {
            throw new IllegalArgumentException("CNPJ is already registered.");
        }

    }


/*
    public void UpdateUserBusiness(UUID id, UpdateUserIndividualDTO atualizarUsuarioDTO){


        UserIndividualModel usuario = userBusinessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));


        if(atualizarUsuarioDTO.getNomeCompleto()!= null){
            usuario.setNomeCompleto(atualizarUsuarioDTO.getNomeCompleto());
        }

        if (atualizarUsuarioDTO.getDataNascimento()!=null){
            validarDataNascimento(atualizarUsuarioDTO.getDataNascimento());
            usuario.setDataNascimento(atualizarUsuarioDTO.getDataNascimento());
        }


        if (atualizarUsuarioDTO.getTelefone1() != null ||
                atualizarUsuarioDTO.getTelefone2() != null ||
                atualizarUsuarioDTO.getTelefone3() != null) {

            if (atualizarUsuarioDTO.getTelefone1() !=null){
                usuario.setTelefone1(atualizarUsuarioDTO.getTelefone1());
            }

            if (atualizarUsuarioDTO.getTelefone2() !=null){
                usuario.setTelefone2(atualizarUsuarioDTO.getTelefone2());
            }

            if (atualizarUsuarioDTO.getTelefone2() !=null){
                usuario.setTelefone3(atualizarUsuarioDTO.getTelefone3());
            }

            validarTelefones(usuario.getTelefone1(),
                    usuario.getTelefone2(),
                    usuario.getTelefone3());

        }

        userBusinessRepository.save(usuario);

    }

 */

}



