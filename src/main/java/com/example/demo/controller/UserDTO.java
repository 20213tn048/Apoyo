package com.example.demo.controller;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @Pattern(regexp = "^[a-zA-ZñÑ ]+$", message = "El nombre no debe de contener caracteres especiales")
    @Length(max = 30, message = "El nombre no debe de pasar de 30 caractéres")
    @NotEmpty(message = "Campo Nombre es obligatorio")
    private String name;

    @Pattern(regexp = "^[a-zA-ZñÑ ]+$", message = "El apellido no debe de contener caracteres especiales")
    @Length(max = 30, message = "El apellido no debe de pasar de 30 caracteres")
    @NotEmpty(message = "Campo Apellido es obligatorio")
    private String lastname;

    @Email(message = "Corre electrónico no valido")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@gmail\\.[a-zA-Z]{2,}$", message = "El correo electrónico debe ser de Gmail")
    @Length(max = 50, message = "El correo no debe de sobrepasar de 50 caracteres")
    @NotEmpty(message = "Campo correo es obligatorio")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "La contraseña no puede estar en blanco")
    @Length(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
 
    private Date birthday;
}
