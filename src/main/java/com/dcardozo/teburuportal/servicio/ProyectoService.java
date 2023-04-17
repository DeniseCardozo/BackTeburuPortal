package com.dcardozo.teburuportal.servicio;

import com.dcardozo.teburuportal.dominio.Proyecto;
import com.dcardozo.teburuportal.dto.ProyectoDTO;
import com.dcardozo.teburuportal.exception.ErrorProcessException;

import java.util.List;

public interface ProyectoService {
    List<Proyecto> getAllProyectosByIdArea(Integer id_area) throws ErrorProcessException;

    ProyectoDTO buscarProyectoByIdServicio(Integer id_proyecto) throws ErrorProcessException;

    ProyectoDTO crearProyectoServicio(Proyecto proyecto) throws ErrorProcessException;

}
