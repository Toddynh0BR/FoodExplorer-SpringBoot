package com.example.foodexplorer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodexplorer.entity.User;
import com.example.foodexplorer.repository.UserRepository;

@RestController
@RequestMapping("/users")

public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        System.out.println("Buscando informações do usuário com id: " + id);

        if (id == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).build();
        }

        return userRepository.findById(id)
                             .map(ResponseEntity::ok)
                             .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser( @RequestBody User user) {
        User savedUser = userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);
    }

    @PatchMapping
    public String editUser() {
        return "Editado";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id) {
        return "Atualizado";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "Deletado";
    }

}
