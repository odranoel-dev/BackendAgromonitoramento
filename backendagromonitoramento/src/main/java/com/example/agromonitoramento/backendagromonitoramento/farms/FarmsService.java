package com.example.agromonitoramento.backendagromonitoramento.farms;

import com.example.agromonitoramento.backendagromonitoramento.farms.dto.CreateFarmsDTO;
import com.example.agromonitoramento.backendagromonitoramento.farms.dto.ListFarmsResponseDTO;
import com.example.agromonitoramento.backendagromonitoramento.farms.dto.UpdateFarmsRequestDTO;
import com.example.agromonitoramento.backendagromonitoramento.farms.dto.UpdateFarmsResponseDTO;
import com.example.agromonitoramento.backendagromonitoramento.users.business.UserBusinessModel;
import com.example.agromonitoramento.backendagromonitoramento.users.business.UserBusinessRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FarmsService {

    @Autowired
    private FarmsRepository farmsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserBusinessRepository userBusinessRepository;

    public void createFarms(CreateFarmsDTO dto, UserBusinessModel currentUser) {

        FarmsModel farmsModel = new FarmsModel();
        farmsModel.setName(dto.getName());
        farmsModel.setUserBusiness(currentUser);
        farmsModel.setIsActive(true);

        farmsRepository.save(farmsModel);
    }


    public List<ListFarmsResponseDTO> listFarmsByUser(UUID userBusinessId){
        return farmsRepository.findByUserBusinessId(userBusinessId)
                .stream()
                .map(f -> {
                    ListFarmsResponseDTO dto = new ListFarmsResponseDTO();
                    dto.setName(f.getName());
                    dto.setIsActive(f.getIsActive());
                    return dto;
                })
                .collect(Collectors.toList());
    }



    public UpdateFarmsResponseDTO updateFarms(UpdateFarmsRequestDTO dto, UUID farmId, UserBusinessModel currentUser) {

        FarmsModel farm = farmsRepository.findById(farmId)
                .orElseThrow(() -> new RuntimeException("Fazenda não encontrada"));

        // Verifica se o usuário logado é o dono da fazenda
        if (!farm.getUserBusiness().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Você não tem permissão para atualizar esta fazenda");
        }

        if(dto.getName() != null){
            farm.setName(dto.getName());
        }

        if (dto.getIsActive() != null){
            farm.setIsActive(dto.getIsActive());
        }


        farmsRepository.save(farm);

        return new UpdateFarmsResponseDTO(farm.getName(), farm.getIsActive());
    }


}
