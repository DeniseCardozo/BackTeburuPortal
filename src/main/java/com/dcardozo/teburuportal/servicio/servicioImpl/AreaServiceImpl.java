package com.dcardozo.teburuportal.servicio.servicioImpl;

import com.dcardozo.teburuportal.dominio.Area;
import com.dcardozo.teburuportal.repositorio.AreaRepository;
import com.dcardozo.teburuportal.servicio.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    private final AreaRepository repository;
    @Autowired
    public AreaServiceImpl(AreaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Area> getAll() {
        return repository.findAll();
    }

    @Override
    public Area buscarByIdServicio(Integer id_area) {
        return repository.areaById(id_area);
    }

    @Override
    public Area crearAreaServicio(Area area) {
        return repository.save(area);
    }

    @Override
    public ResponseEntity<?> findNombreArea(String nombre) {
        Area area = repository.findAreaByNombre(nombre);
        if (area != null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        } else {
            return new ResponseEntity<>(HttpStatus.FOUND) ;
        }
    }
}
