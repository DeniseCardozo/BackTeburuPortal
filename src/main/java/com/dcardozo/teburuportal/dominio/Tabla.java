package com.dcardozo.teburuportal.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tablas")
public class Tabla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tabla")
    private Integer id_tabla;
    @Column(length = 100, nullable = false)
    @NotBlank(message = "Tabla name cannot be null or empty")
    @NotEmpty(message = "Tabla name cannot be null or empty")
    private String nombre;
    @Column(nullable = false)
    @NotBlank(message = "Tabla esquema cannot be null or empty")
    @NotEmpty(message = "Tabla esquema cannot be null or empty")
    private String esquema;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_area")
    Area area;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_proyecto")
    Proyecto proyecto;
}
