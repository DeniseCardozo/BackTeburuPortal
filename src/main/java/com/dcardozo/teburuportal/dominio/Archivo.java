package com.dcardozo.teburuportal.dominio;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "archivos")
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo")
    private Integer id_archivo;
    @Lob
    @Column(nullable = false)
    private byte[] archivo;
    @Column(name = "ultima_actualizacion", nullable = false)
    private Date ultima_actualizacion;
    @Column(name = "nombre_Tabla", length = 100, nullable = false)
    private String nombre_Tabla;
    @Column(name = "id_usuario", nullable = false)
    private Integer id_usuario;
    @Column(name = "id_tabla", nullable = false)
    private  Integer id_tabla;
}
