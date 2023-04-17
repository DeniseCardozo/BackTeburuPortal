package com.dcardozo.teburuportal.servicio.servicioImpl;

import com.dcardozo.teburuportal.dominio.Area;
import com.dcardozo.teburuportal.dto.AreaDTO;
import com.dcardozo.teburuportal.dto.mapper.AreaMapper;
import com.dcardozo.teburuportal.exception.BadRequestException;
import com.dcardozo.teburuportal.exception.ErrorProcessException;
import com.dcardozo.teburuportal.exception.NotFoundException;
import com.dcardozo.teburuportal.repositorio.AreaRepository;
import com.dcardozo.teburuportal.servicio.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AreaServiceImpl implements AreaService {
    private final String ERROR_NOT_FOUND = "During the process an error occurred: ";
    private final AreaRepository repository;
    private final AreaMapper areaMapper;

    @Override
    public List<Area> getAll() throws ErrorProcessException {
        try {
            return repository.findAll();
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public AreaDTO buscarByIdServicio(Integer id_area) throws ErrorProcessException  {
        Area foundArea = (repository.areaById(id_area)).orElseThrow(() -> new NotFoundException("Area id: " + id_area + ", not found in database"));
        try {
            return areaMapper.entityToAreaDTO(foundArea);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public AreaDTO crearAreaServicio(Area area) throws ErrorProcessException  {
        if (repository.findAreaByNombre(area.getNombre()) != null) {
            throw new BadRequestException("Existing area in database");
        }
        try {
            return areaMapper.entityToAreaDTO(repository.save(area));
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }
}
