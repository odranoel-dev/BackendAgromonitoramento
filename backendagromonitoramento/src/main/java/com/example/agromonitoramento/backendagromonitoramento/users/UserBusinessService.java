package com.example.agromonitoramento.backendagromonitoramento.users;

import com.example.agromonitoramento.backendagromonitoramento.validations.EmailValidationService;
import com.example.agromonitoramento.backendagromonitoramento.validations.PasswordValidationService;
import com.example.agromonitoramento.backendagromonitoramento.validations.PhoneNumberValidationService;
import com.example.agromonitoramento.backendagromonitoramento.users.dto.RegisterUserBusinessDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.dto.UpdateUserBusinessRequestDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.dto.UpdateUserBusinessResponseDTO;
import com.example.agromonitoramento.backendagromonitoramento.whatsapp.WhatsappService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


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

    @Autowired
    private ModelMapper modelMapper;


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
        whatsAppService.enviarMensagemBoasVindas(ownerName,phoneNumber);
    }

    public void validateCnpj(String cnpj) {

        //Verifica se cnpj já existe no banco, consultando no repositório.

        if (userBusinessRepository.existsByCnpj(cnpj)) {
            throw new IllegalArgumentException("CNPJ is already registered.");
        }

    }

    public UpdateUserBusinessResponseDTO updateUserBusiness(UUID id, UpdateUserBusinessRequestDTO updateUserBusinessRequestDTO){

        UserBusinessModel usuario = userBusinessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(updateUserBusinessRequestDTO.getName()!= null){
            usuario.setName(updateUserBusinessRequestDTO.getName());
        }


        boolean isBusinessNameEmpty = updateUserBusinessRequestDTO.getBusinessName() == null ||
                updateUserBusinessRequestDTO.getBusinessName().isBlank();

        if (!isBusinessNameEmpty){
            usuario.setBusinessName(updateUserBusinessRequestDTO.getBusinessName());
        }

        if (updateUserBusinessRequestDTO.getPhoneNumber() != null){
            phoneNumberValidationService.validatePhoneNumber(updateUserBusinessRequestDTO.getPhoneNumber());
            usuario.setPhoneNumber(updateUserBusinessRequestDTO.getPhoneNumber());
        }

        if (updateUserBusinessRequestDTO.getEmail() != null){
            emailValidationService.emailValidation(updateUserBusinessRequestDTO.getEmail());
            usuario.setEmail(updateUserBusinessRequestDTO.getEmail());
        }

        userBusinessRepository.save(usuario);

        return modelMapper.map(usuario,UpdateUserBusinessResponseDTO.class);
    }
}



