package com.example.foodexplorer.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.foodexplorer.entity.User;
import com.example.foodexplorer.repository.UserRepository;
import com.example.foodexplorer.exception.AppException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
         throw new AppException(409, "Email já cadastrado");
        }//Verificação de email repetido

        LocalDateTime now = LocalDateTime.now();//Data atual

        user.setPassword(
            passwordEncoder.encode(user.getPassword())
        );//Criptografia da senha

        user.setIsAdmin(false);//Definição de is_admin
        user.setCreatedAt(now);//Definição de created_at
        user.setUpdatedAt(now);//Definição de updated_at

        return userRepository.save(user);
    };

    public User updateUser(Long id, User userData) {
     User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(
                    404,
                    "Usuário não encontrado"
                ));//caso não haja user

     //atualizar dados somente se foram enviados
     if (userData.getName() != null) user.setName(userData.getName());
     if (userData.getEmail() != null) user.setEmail(userData.getEmail());
     if (userData.getPassword() != null) user.setPassword(passwordEncoder.encode(userData.getPassword()));
     if (userData.getIsAdmin() != null) user.setIsAdmin(userData.getIsAdmin());

     LocalDateTime now = LocalDateTime.now();//Data atual
     user.setUpdatedAt(now);//atualziar data de atualização

     return userRepository.save(user);//retornar user salvo
    };

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                                   .orElseThrow(() ->
                                     new AppException(404,"Usuário não encontrado")
                                   );
                                   
        userRepository.delete(user);
    }
}