package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.controller.UserDTO;
import com.example.demo.model.User;
import com.example.demo.model.UserRepository;
import com.example.demo.utils.CustomResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CustomResponse saveUser(UserDTO userDTO) {
        try {
            if (userDTO.getPassword().length() < 8) {
                return new CustomResponse(null, true, HttpStatus.CONFLICT.value(),
                        "La contraseña debe tener mínimo 8 caracteres");
            } else {
                User newUser = new User();

                newUser.setName(userDTO.getName());
                newUser.setLastname(userDTO.getLastname());
                newUser.setEmail(userDTO.getEmail());
                newUser.setPassword(userDTO.getPassword());
                newUser.setBirthday(userDTO.getBirthday());
                this.userRepository.save(newUser);
                return new CustomResponse(userDTO, false, HttpStatus.CREATED.value(),
                        "Usuario Registrado exitosamente");
            }
        } catch (Exception e) {
            log.error("Error al guardar el usuario: ", e.getMessage());
            return new CustomResponse(null, true, HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error al guardar el usuario");
        }
    }

}
