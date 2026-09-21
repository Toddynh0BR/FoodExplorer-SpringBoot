package com.example.foodexplorer.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.foodexplorer.entity.Plate;
import com.example.foodexplorer.repository.PlateRepository;
import com.example.foodexplorer.exception.AppException;

@Service
public class PlateServicervice {
    private final PlateRepository plateRepository;
    public PlateServicervice(PlateRepository plateRepository) {
        this.plateRepository = plateRepository;
    }

    public Plate createPlate(Plate plate) {


     

        return plateRepository.save(plate);
    };

    public Plate updatePlate(Long id, Plate plateData) {
     Plate plate = plateRepository.findById(id)
                .orElseThrow(() -> new AppException(
                    404,
                    "Prato não encontrado"
                ));//caso não haja plate

     //atualizar dados somente se foram enviados
     if (plateData.getName() != null) plate.setName(plateData.getName());
     
     plate.setUpdatedAt(now);//atualziar data de atualização

     return plateRepository.save(plate);//retornar plate salvo
    };

    public void deletePlate(Long id) {
        Plate plate = plateRepository.findById(id)
                                   .orElseThrow(() ->
                                     new AppException(404,"Prato não encontrado")
                                   );
                                   
        plateRepository.delete(plate);
    }
}