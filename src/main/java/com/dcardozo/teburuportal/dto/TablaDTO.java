package com.dcardozo.teburuportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TablaDTO {
    private Integer id_tabla;
    private String nombre;
    private String esquema;
}
