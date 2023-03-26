package com.dcardozo.teburuportal.servicio;

import com.dcardozo.teburuportal.dominio.Area;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AreaService {
    List<Area> getAll();

    Area buscarByIdServicio(Integer id_area);

    Area crearAreaServicio(Area area);

    ResponseEntity<?> findNombreArea(String nombre);
}
