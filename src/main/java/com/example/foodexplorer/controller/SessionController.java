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
import org.springframework.web.bind.annotation.RestController;

import com.example.foodexplorer.entity.User;
import com.example.foodexplorer.service.UserService;
import com.example.foodexplorer.exception.AppException;
import com.example.foodexplorer.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/session")

public class SessionController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserService userService;

    public SessionController(
        UserRepository userRepository,
        UserService userService,
        PasswordEncoder passwordEncoder
                            ) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userService = userService;
    };


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User request) {
      User user = userRepository.findByEmail(request.getEmail())
                                .orElseThrow(()->
                                 new AppException(401, "Email ou senha incorretos");
                                );

     boolean senhaValida = passwordEncoder.matches(request.getPassword(), user.getPassword());

     if (!senhaValida) {
        throw new AppException(401, "Email ou senha incorretos");
     }

     return ResponseEntity.ok(user);
    };
}
