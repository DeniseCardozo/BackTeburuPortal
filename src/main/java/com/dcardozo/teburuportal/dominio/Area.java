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
@Table(name = "areas")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Integer id_area;
    @Column(length = 100, nullable = false)
    @NotBlank(message = "Area name cannot be null or empty")
    @NotEmpty(message = "Area name cannot be null or empty")
    private String nombre;
}
