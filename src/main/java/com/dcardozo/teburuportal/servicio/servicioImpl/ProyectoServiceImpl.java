package com.dcardozo.teburuportal.servicio.servicioImpl;

import com.dcardozo.teburuportal.dominio.Proyecto;
import com.dcardozo.teburuportal.dto.ProyectoDTO;
import com.dcardozo.teburuportal.dto.mapper.ProyectoMapper;
import com.dcardozo.teburuportal.exception.BadRequestException;
import com.dcardozo.teburuportal.exception.ErrorProcessException;
import com.dcardozo.teburuportal.exception.NotFoundException;
import com.dcardozo.teburuportal.repositorio.ProyectoRepository;
import com.dcardozo.teburuportal.servicio.ProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProyectoServiceImpl implements ProyectoService {
    private final String ERROR_NOT_FOUND = "During the process an error occurred: ";
    private final ProyectoRepository repository;
    private final ProyectoMapper proyectoMapper;

    @Override
    public List<Proyecto> getAllProyectosByIdArea(Integer id_area) throws ErrorProcessException {
        try {
            return repository.proyectosByArea(id_area);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public ProyectoDTO buscarProyectoByIdServicio(Integer id_proyecto) throws ErrorProcessException {
        Proyecto foundProyecto = (repository.proyectoById(id_proyecto)).orElseThrow(() -> new NotFoundException("Proyecto id: " + id_proyecto + ", not found in database"));
        try {
            return proyectoMapper.entityToProyectoDTO(foundProyecto);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public ProyectoDTO crearProyectoServicio(Proyecto proyecto) throws ErrorProcessException {
        if (repository.findProyectoByNombre(proyecto.getNombre()) != null) {
            throw new BadRequestException("Existing proyecto in database");
        }
        try {
            return proyectoMapper.entityToProyectoDTO(repository.save(proyecto));
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }
}
