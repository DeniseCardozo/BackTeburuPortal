package com.dcardozo.teburuportal.dto.mapper;

import com.dcardozo.teburuportal.dominio.Area;
import com.dcardozo.teburuportal.dto.AreaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaMapper {
    AreaDTO entityToAreaDTO(Area entity);
}
