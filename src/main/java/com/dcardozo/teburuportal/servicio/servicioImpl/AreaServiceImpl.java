package com.dcardozo.teburuportal.servicio.servicioImpl;

import com.dcardozo.teburuportal.dominio.Area;
import com.dcardozo.teburuportal.exception.BadRequestException;
import com.dcardozo.teburuportal.exception.ErrorProcessException;
import com.dcardozo.teburuportal.exception.NotFoundException;
import com.dcardozo.teburuportal.repositorio.AreaRepository;
import com.dcardozo.teburuportal.servicio.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    private final String ERROR_NOT_FOUND = "During the process an error occurred: ";
    private final AreaRepository repository;
    @Autowired
    public AreaServiceImpl(AreaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Area> getAll() throws ErrorProcessException {
        try {
            return repository.findAll();
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public Area buscarByIdServicio(Integer id_area) throws ErrorProcessException  {
        Area foundArea = (repository.areaById(id_area)).orElseThrow(() -> new NotFoundException("Area id: " + id_area + ", not found in database"));
        try {
            return foundArea;
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public Area crearAreaServicio(Area area) throws ErrorProcessException  {
        if (repository.findAreaByNombre(area.getNombre()) != null) {
            throw new BadRequestException("Existing area in database");
        }
        try {
            return repository.save(area);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }
}
