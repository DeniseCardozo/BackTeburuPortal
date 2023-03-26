package com.dcardozo.teburuportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPosLoginwithIdDTO {
    private Integer id_usuario;
    private String email;
    private Integer rol;

}
