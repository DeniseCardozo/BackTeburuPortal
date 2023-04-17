package com.dcardozo.teburuportal.dto.mapper;

import com.dcardozo.teburuportal.dominio.Tabla;
import com.dcardozo.teburuportal.dto.TablaCreatedOrUpdatedDTO;
import com.dcardozo.teburuportal.dto.TablaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TablaMapper {
    TablaDTO entityToTablaDTO(Tabla entity);
    TablaCreatedOrUpdatedDTO entityToTablaCreatedOrUpdatedDTO(Tabla entity);

}
