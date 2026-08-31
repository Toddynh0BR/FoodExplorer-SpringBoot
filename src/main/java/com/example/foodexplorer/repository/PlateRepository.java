package com.example.foodexplorer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodexplorer.entity.Plate;

public interface PlateRepository extends JpaRepository<Plate, Long> {
}