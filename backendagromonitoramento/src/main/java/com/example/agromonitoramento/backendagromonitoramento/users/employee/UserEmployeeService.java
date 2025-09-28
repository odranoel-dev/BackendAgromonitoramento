package com.example.agromonitoramento.backendagromonitoramento.users.employee;

import com.example.agromonitoramento.backendagromonitoramento.farms.FarmsModel;
import com.example.agromonitoramento.backendagromonitoramento.farms.FarmsRepository;
import com.example.agromonitoramento.backendagromonitoramento.users.employee.dto.CreateUserEmployeeDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.employee.dto.ListUserEmployeeDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.employee.dto.UpdateUserEmployeeRequestDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.employee.dto.UpdateUserEmployeeResponseDTO;
import com.example.agromonitoramento.backendagromonitoramento.validations.PhoneNumberValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserEmployeeService {

    @Autowired
    private UserEmployeeRepository userEmployeeRepository;


    @Autowired
    private PhoneNumberValidationService phoneNumberValidationService;

    @Autowired
    private FarmsRepository farmsRepository;

    @Autowired
    private ModelMapper modelMapper;


    public void createdEmployee(CreateUserEmployeeDTO createUserEmployeeDTO) {

        /*
        consultar no banco quantos telefones possuem cadastrados, para bloquear quando chegar no limite

        Se employee estiver inativo, n permitir o chat processar msg
         */

        FarmsModel farm = farmsRepository.findById(createUserEmployeeDTO.getFarmId())
                .orElseThrow(() -> new IllegalArgumentException("Farm not found"));

        String phoneNumber = createUserEmployeeDTO.getPhoneNumber();
        phoneNumberValidationService.validatePhoneNumber(phoneNumber);

        UserEmployeeModel userEmployeeModel = new UserEmployeeModel();

        userEmployeeModel.setFarm(farm);
        userEmployeeModel.setName(createUserEmployeeDTO.getName());
        userEmployeeModel.setPosition(createUserEmployeeDTO.getPosition());
        userEmployeeModel.setPhoneNumber(phoneNumber);
        userEmployeeRepository.save(userEmployeeModel);


    }

    public UpdateUserEmployeeResponseDTO updateEmployee(UUID id, UpdateUserEmployeeRequestDTO updateUserEmployeeRequestDTO) {

        UserEmployeeModel employee = userEmployeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));


        if (updateUserEmployeeRequestDTO.getName() != null) {
            employee.setName(updateUserEmployeeRequestDTO.getName());
        }

        if (updateUserEmployeeRequestDTO.getIsActive() != null) {
            employee.setActive(updateUserEmployeeRequestDTO.getIsActive());
        }

        if (updateUserEmployeeRequestDTO.getPosition() != null) {
            employee.setPosition(updateUserEmployeeRequestDTO.getPosition());
        }

        if (updateUserEmployeeRequestDTO.getFarmId() != null) {

            FarmsModel farm = farmsRepository.findById(updateUserEmployeeRequestDTO.getFarmId())
                    .orElseThrow(() -> new IllegalArgumentException("Farm not found"));

            employee.setFarm(farm);
        }

        userEmployeeRepository.save(employee);

        return modelMapper.map(employee, UpdateUserEmployeeResponseDTO.class);
    }

    public List<ListUserEmployeeDTO> getEmployeesByFarmId(UUID farmId) {

        List<ListUserEmployeeDTO> listEmployee = userEmployeeRepository.findAllByFarmId(farmId);

        return listEmployee;
    }
}
