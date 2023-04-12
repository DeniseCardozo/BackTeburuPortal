package com.dcardozo.teburuportal.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id_usuario;
    @NotBlank(message = "Email cannot be null or empty")
    @NotEmpty(message = "Email cannot be null or empty")
    @Column(length = 100, unique = true, nullable = false)
    private String email;
    @NotBlank(message = "Contrasena cannot be null or empty")
    @NotEmpty(message = "Contrasena cannot be null or empty")
    @Size(min = 8, max = 8, message = "Contraseña must be 8 characters")
    @Column(length = 8, nullable = false)
    private String contrasena;
    @Column(nullable = false)
    private Integer rol;

}