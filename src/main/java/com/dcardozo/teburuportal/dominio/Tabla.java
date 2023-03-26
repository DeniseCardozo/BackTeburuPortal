package com.dcardozo.teburuportal.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String nombre;
    @Column(nullable = false)
    private String esquema;
//    @Column(name = "caracteres_especiales", nullable = false)
//    private String caracteres_especiales;
//    @ManyToOne(
////            cascade = CascadeType.ALL
//    )
//    @JoinColumn(name = "id_area")
//    Area area;
//    @ManyToOne(
////        cascade = CascadeType.ALL
//    )
//    @JoinColumn(name = "id_proyecto")
//    Proyecto proyecto;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_area")
    Area area;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_proyecto")
    Proyecto proyecto;
}
