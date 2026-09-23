package com.example.foodexplorer.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.example.foodexplorer.entity.Plate;
import com.example.foodexplorer.service.PlateService;
import com.example.foodexplorer.exception.AppException;
import com.example.foodexplorer.repository.PlateRepository;
import com.example.foodexplorer.service.FileStorageService;

@RestController
@RequestMapping("/plates")

public class PlateController {
    private final FileStorageService fileStorageService;
    private final PlateRepository plateRepository;
    private final PlateService plateService;

    public PlateController(
          FileStorageService fileStorageService,
          PlateRepository plateRepository,
          PlateService plateService
        ) {
        this.fileStorageService = fileStorageService;
        this.plateRepository = plateRepository;
        this.plateService = plateService;
    };

    @GetMapping("/{id}")
    public ResponseEntity<Plate> getPlate(@PathVariable Long id) {
        System.out.println("Buscando informações do prato com id: " + id);

        if (id == null) {
         throw new AppException(422, "ID do prato necessário");
        }

        return plateRepository.findById(id)
                             .map(ResponseEntity::ok)
                             .orElse(ResponseEntity.notFound().build());
    };

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Plate> createPlate(
        @RequestParam("name") String name,
        @RequestParam("price") Double price,
        @RequestParam("category") String category,
        @RequestParam("image") MultipartFile image,
        @RequestParam("description") String description,
        Plate plate
    ) {
        
        String filename = fileStorageService.save(image);

        String imageUrl = "/uploads/" + filename;

        plate.setName(name);
        plate.setPrice(price);
        plate.setImg(imageUrl);
        plate.setCategory(category);
        plate.setDescription(description);

        plateRepository.save(plate);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(plate);
    };

    @PutMapping("/{id}")
    public ResponseEntity<Plate> updatePlate(@PathVariable Long id, @RequestBody Plate plate) {
        Plate plateUpdated = plateService.updatePlate(id, plate);
                                      
        return ResponseEntity.ok(plateUpdated);
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Plate> deletePlate(@PathVariable Long id) {
        plateService.deletePlate(id);

        return ResponseEntity.noContent().build();
    };

}
