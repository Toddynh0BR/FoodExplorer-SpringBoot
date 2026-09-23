package com.example.foodexplorer.service;

import org.springframework.stereotype.Service;

import com.example.foodexplorer.entity.Plate;
import com.example.foodexplorer.repository.PlateRepository;
import com.example.foodexplorer.exception.AppException;

@Service
public class PlateService {
    private final PlateRepository plateRepository;
    public PlateService(PlateRepository plateRepository) {
        this.plateRepository = plateRepository;
    }

    public Plate createPlate(Plate plate) {

        plate.setName(plate.getName());
        plate.setPrice(plate.getPrice());
        plate.setCategory(plate.getCategory());
        plate.setDescription(plate.getDescription());

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