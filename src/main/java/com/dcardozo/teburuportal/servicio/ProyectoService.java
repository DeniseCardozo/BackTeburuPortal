package com.dcardozo.teburuportal.servicio;

import com.dcardozo.teburuportal.dominio.Proyecto;

import java.util.List;

public interface ProyectoService {
    List<Proyecto> getAllProyectosByIdArea(Integer id_area);

    Proyecto buscarProyectoByIdServicio(Integer id_proyecto);

    Proyecto crearProyectoServicio(Proyecto proyecto);

}
