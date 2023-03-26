package com.dcardozo.teburuportal.servicio;

import com.dcardozo.teburuportal.dominio.Tabla;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TablaService {
    List<Tabla> getAllTablasByIdProyecto(Integer id_proyecto);

    String getEsquema(Integer id_tabla);

    List<String[]> getEsquemaProcesado(Integer id_tabla);

    String getNombre(Integer id_tabla);

    Tabla getTablaById(Integer id_tabla);

    Tabla updateTabla(Integer id_tabla, Tabla tabla);

    Tabla crearTablaServicio(Tabla tabla);

    ResponseEntity<?> deleteTablaById(Integer idTabla);
}
