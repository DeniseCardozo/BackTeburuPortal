package com.dcardozo.teburuportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLogInDTO {
    @NotBlank(message = "Email cannot be null or empty")
    @NotEmpty(message = "Email cannot be null or empty")
    private String email;

    @NotBlank(message = "Contrasena cannot be null or empty")
    @NotEmpty(message = "Contrasena cannot be null or empty")
    @Size(min = 8, max = 8, message = "Contraseña must be 8 characters")
    private String contrasena;

}
