package com.dcardozo.teburuportal.servicio.servicioImpl;

import com.dcardozo.teburuportal.dominio.Tabla;
import com.dcardozo.teburuportal.repositorio.TablaRepository;
import com.dcardozo.teburuportal.servicio.TablaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TablaServiceImpl implements TablaService {

    Tabla tablaActualizada = new Tabla();
    private final TablaRepository repository;

    @Autowired
    public TablaServiceImpl(TablaRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Tabla> getAllTablasByIdProyecto(Integer id_proyecto) {
        return repository.tablasByProyecto(id_proyecto);
    }

    @Override
    public String getEsquema(Integer id_tabla) {
        return repository.esquemabyIdTabla(id_tabla);
    }

    @Override
    public List<String[]> getEsquemaProcesado(Integer id_tabla) {
        String[] primerdivision = repository.esquemabyIdTabla(id_tabla).split("-");
        List<String[]> segundaDivision = new ArrayList<>() ;

        for (String elem : primerdivision) {
            segundaDivision.add(elem.split(","));
        }
        return segundaDivision;
    }

    @Override
    public String getNombre(Integer id_tabla) {
        return repository.nombrebyIdTabla(id_tabla);
    }

    @Override
    public Tabla getTablaById(Integer id_tabla) {
        return repository.getTablaById(id_tabla);
    }

    @Override
    public Tabla updateTabla(Integer id_tabla, Tabla tabla) {
            System.out.println(tabla);
            Optional<Tabla> optionalTabla = repository.findById(id_tabla);

            if (optionalTabla.isPresent()){
                tablaActualizada = optionalTabla.get();
            } else {
                throw new RuntimeException("Tabla " + tabla.getNombre() + "no existente");
            }

            tablaActualizada.setNombre(tabla.getNombre());
            tablaActualizada.setEsquema(tabla.getEsquema());
//            tablaActualizada.setCaracteres_especiales(tabla.getCaracteres_especiales());

        return repository.save(tablaActualizada);
    }

    @Override
    public Tabla crearTablaServicio(Tabla tabla) {
        return repository.save(tabla);
    }

    @Override
    public ResponseEntity<?> deleteTablaById(Integer id_tabla) {
        Optional<Tabla> optionalTabla = repository.findById(id_tabla);
        if (optionalTabla.isPresent()){
            repository.deleteById(id_tabla);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new RuntimeException("Tabla de id" + id_tabla + "no existente");
        }
    }


}
