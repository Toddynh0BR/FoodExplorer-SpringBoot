package com.example.foodexplorer.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.foodexplorer.entity.User;
import com.example.foodexplorer.repository.UserRepository;
import com.example.foodexplorer.exception.AppException;

@Service
public class SessionService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SessionService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                                .orElseThrow(()->
                                 new AppException(401, "Email ou senha incorretos")
                                );

     boolean senhaValida = passwordEncoder.matches(password, user.getPassword());

     if (!senhaValida) {
        throw new AppException(401, "Email ou senha incorretos");
     }

     return user;
    }
}
