package com.dcardozo.teburuportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSignUpDTO {
    @NotBlank(message = "Email cannot be null or empty")
    @NotEmpty(message = "Email cannot be null or empty")
    private String email;
}
