package com.example.foodexplorer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path uploadPath;

    public FileStorageService(
            @Value("${file.upload-dir}") String uploadDir
    ) {
        this.uploadPath = Paths.get(uploadDir)
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar o diretório de uploads", e);
        }
    }

    public String save(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Arquivo inválido");
        }

        String originalName = file.getOriginalFilename();

        String extension = "";

        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(
                    originalName.lastIndexOf(".")
            );
        }

        String filename = UUID.randomUUID() + extension;

        Path target = uploadPath.resolve(filename).normalize();

        // Segurança: impede que o arquivo escape do diretório de uploads
        if (!target.getParent().equals(uploadPath)) {
            throw new RuntimeException("Caminho de arquivo inválido");
        }

        try {
            Files.copy(
                    file.getInputStream(),
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return filename;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo", e);
        }
    }
}