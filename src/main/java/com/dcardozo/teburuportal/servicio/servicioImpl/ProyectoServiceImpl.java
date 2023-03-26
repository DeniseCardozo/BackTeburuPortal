package com.dcardozo.teburuportal.servicio.servicioImpl;

import com.dcardozo.teburuportal.dominio.Proyecto;
import com.dcardozo.teburuportal.repositorio.ProyectoRepository;
import com.dcardozo.teburuportal.servicio.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository repository;
    @Autowired
    public ProyectoServiceImpl(ProyectoRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Proyecto> getAllProyectosByIdArea(Integer id_area) {
        return repository.proyectosByArea(id_area);
    }

    @Override
    public Proyecto buscarProyectoByIdServicio(Integer id_proyecto) {
        return repository.proyectoById(id_proyecto);
    }

    @Override
    public Proyecto crearProyectoServicio(Proyecto proyecto) {
        return repository.save(proyecto);
    }
}
