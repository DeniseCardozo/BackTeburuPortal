package com.dcardozo.teburuportal.dto.mapper;

import com.dcardozo.teburuportal.dominio.Proyecto;
import com.dcardozo.teburuportal.dto.ProyectoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProyectoMapper {
    ProyectoDTO entityToProyectoDTO(Proyecto entity);
}
