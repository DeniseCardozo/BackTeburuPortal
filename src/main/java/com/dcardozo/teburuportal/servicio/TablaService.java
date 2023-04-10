package com.dcardozo.teburuportal.servicio;

import com.dcardozo.teburuportal.dominio.Tabla;
import com.dcardozo.teburuportal.exception.ErrorProcessException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TablaService {
    List<Tabla> getAllTablasByIdProyecto(Integer id_proyecto) throws ErrorProcessException;

    String getEsquema(Integer id_tabla) throws ErrorProcessException;

    List<String[]> getEsquemaProcesado(Integer id_tabla) throws ErrorProcessException;

    String getNombre(Integer id_tabla) throws ErrorProcessException;

    Tabla getTablaById(Integer id_tabla) throws ErrorProcessException;

    Tabla updateTabla(Integer id_tabla, Tabla tabla) throws ErrorProcessException;

    Tabla crearTablaServicio(Tabla tabla) throws ErrorProcessException;

    ResponseEntity<?> deleteTablaById(Integer idTabla) throws ErrorProcessException;
}
