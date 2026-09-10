package com.example.foodexplorer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodexplorer.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContainingIgnoreCase(String name);

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}