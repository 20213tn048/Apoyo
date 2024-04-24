package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;
import com.example.demo.utils.CustomResponse;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = {"*"})
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<CustomResponse> addNewUser(@RequestBody UserDTO userDTO){
        CustomResponse response = this.userService.saveUser(userDTO);
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> isOlderThanEighteen(@PathVariable Long id){
        CustomResponse response = this.userService.isOlderThanEighteen(id);
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }
}
