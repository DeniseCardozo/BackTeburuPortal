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
@Table(name = "areas")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Integer id_area;
    @Column(length = 100, nullable = false)
    private String nombre;
//    @JsonIgnore
//    @OneToMany(mappedBy = "area")
//    List<Tabla> tablas;
}
