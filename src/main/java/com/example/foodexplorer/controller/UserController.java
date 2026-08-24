package com.example.foodexplorer.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")

public class UserController {

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        return "Usuário " + id;
    }

    @PostMapping
    public String createUser() {
        return "Criado";
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
