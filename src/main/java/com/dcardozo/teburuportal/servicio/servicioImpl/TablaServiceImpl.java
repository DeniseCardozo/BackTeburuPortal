package com.dcardozo.teburuportal.servicio.servicioImpl;

import com.dcardozo.teburuportal.dominio.Tabla;
import com.dcardozo.teburuportal.exception.BadRequestException;
import com.dcardozo.teburuportal.exception.ErrorProcessException;
import com.dcardozo.teburuportal.exception.NotFoundException;
import com.dcardozo.teburuportal.repositorio.TablaRepository;
import com.dcardozo.teburuportal.servicio.TablaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TablaServiceImpl implements TablaService {
    private final String ERROR_NOT_FOUND = "During the process an error occurred: ";
    Tabla tablaActualizada = new Tabla();
    private final TablaRepository repository;

    @Autowired
    public TablaServiceImpl(TablaRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Tabla> getAllTablasByIdProyecto(Integer id_proyecto) throws ErrorProcessException {
        try {
            return repository.tablasByProyecto(id_proyecto);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public String getEsquema(Integer id_tabla) throws ErrorProcessException {
        String esquema = repository.esquemabyIdTabla(id_tabla).orElseThrow(() -> new NotFoundException("Esquema Tabla id: " + id_tabla + ", not found in database"));
        try {
            return esquema;
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public List<String[]> getEsquemaProcesado(Integer id_tabla) throws ErrorProcessException {
        String esquema = repository.esquemabyIdTabla(id_tabla).orElseThrow(() -> new NotFoundException("Esquema Tabla id: " + id_tabla + ", not found in database"));
        try {
            String[] primerdivision = esquema.split("-");
            List<String[]> segundaDivision = new ArrayList<>();

            for (String elem : primerdivision) {
                segundaDivision.add(elem.split(","));
            }
            return segundaDivision;
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public String getNombre(Integer id_tabla) throws ErrorProcessException {
        String nombre = repository.nombrebyIdTabla(id_tabla).orElseThrow(() -> new NotFoundException("Name Tabla id: " + id_tabla + ", not found in database"));
        try {
            return nombre;
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public Tabla getTablaById(Integer id_tabla) throws ErrorProcessException {
        Tabla foundTabla = repository.getTablaById(id_tabla).orElseThrow(() -> new NotFoundException("Tabla id: " + id_tabla + ", not found in database"));
        try {
            return foundTabla;
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public Tabla updateTabla(Integer id_tabla, Tabla tabla) throws ErrorProcessException {
        Tabla foundTabla = repository.getTablaById(id_tabla).orElseThrow(() -> new NotFoundException("Tabla " + tabla.getNombre() + " not found in database"));
        try {
            tablaActualizada = foundTabla;
            tablaActualizada.setNombre(tabla.getNombre());
            tablaActualizada.setEsquema(tabla.getEsquema());
            return repository.save(tablaActualizada);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public Tabla crearTablaServicio(Tabla tabla) throws ErrorProcessException {
        if (repository.findTablaByNombre(tabla.getNombre()) != null) {
            throw new BadRequestException("Existing tabla in database");
        }
        try {
            return repository.save(tabla);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteTablaById(Integer id_tabla) throws ErrorProcessException {
        Tabla foundTabla = repository.getTablaById(id_tabla).orElseThrow(() -> new NotFoundException("Tabla id: " + id_tabla + " not found in database"));
        try {
            repository.deleteById(foundTabla.getId_tabla());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }

    }


}
