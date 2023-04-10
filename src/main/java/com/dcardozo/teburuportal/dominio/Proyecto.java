package com.dcardozo.teburuportal.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Integer id_proyecto;
    @Column(length = 100, nullable = false)
    @NotBlank(message = "Proyecto name cannot be null or empty")
    @NotEmpty(message = "Proyecto name cannot be null or empty")
    private String nombre;
    @JsonIgnore
    @OneToMany(mappedBy = "proyecto")
    List<Tabla> tablas;

}
