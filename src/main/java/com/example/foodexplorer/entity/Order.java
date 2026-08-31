package com.example.foodexplorer.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Float user_id;
    
    @Column(nullable = false)
    private String status;
    
    @Column(nullable = false)
    private Double total;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}