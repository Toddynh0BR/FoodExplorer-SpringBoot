package com.example.foodexplorer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodexplorer.entity.User;
import com.example.foodexplorer.dto.LoginRequest;
import com.example.foodexplorer.service.SessionService;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    };

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {
     User user = sessionService.login(request.getEmail(), request.getPassword());

     return ResponseEntity.ok(user);
    };

}
