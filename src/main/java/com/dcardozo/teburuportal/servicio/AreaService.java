package com.dcardozo.teburuportal.servicio;

import com.dcardozo.teburuportal.dominio.Area;
import com.dcardozo.teburuportal.dto.AreaDTO;
import com.dcardozo.teburuportal.exception.ErrorProcessException;

import java.util.List;

public interface AreaService {
    List<Area> getAll() throws ErrorProcessException;

    AreaDTO buscarByIdServicio(Integer id_area) throws ErrorProcessException;

    AreaDTO crearAreaServicio(Area area) throws ErrorProcessException;

}
