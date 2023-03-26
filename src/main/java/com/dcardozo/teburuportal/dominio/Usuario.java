package com.dcardozo.teburuportal.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Column(length = 100, unique = true, nullable = false)
    private String email;
    @Column(length = 8, nullable = false)
    private String contrasena;
    @Column(nullable = false)
    private Integer rol;

}