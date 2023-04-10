package com.dcardozo.teburuportal.servicio;

import com.dcardozo.teburuportal.dominio.Proyecto;
import com.dcardozo.teburuportal.exception.ErrorProcessException;

import java.util.List;

public interface ProyectoService {
    List<Proyecto> getAllProyectosByIdArea(Integer id_area) throws ErrorProcessException;

    Proyecto buscarProyectoByIdServicio(Integer id_proyecto) throws ErrorProcessException;

    Proyecto crearProyectoServicio(Proyecto proyecto) throws ErrorProcessException;

}
