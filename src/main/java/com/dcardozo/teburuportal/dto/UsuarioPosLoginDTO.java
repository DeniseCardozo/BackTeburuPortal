package com.dcardozo.teburuportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPosLoginDTO {
    private String email;
    private Integer rol;

}
