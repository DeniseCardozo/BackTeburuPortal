package com.dcardozo.teburuportal.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String nombre;
    @JsonIgnore
    @OneToMany(mappedBy = "proyecto")
    List<Tabla> tablas;

}
