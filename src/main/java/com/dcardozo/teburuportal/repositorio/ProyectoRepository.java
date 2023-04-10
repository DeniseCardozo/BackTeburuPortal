package com.dcardozo.teburuportal.repositorio;

import com.dcardozo.teburuportal.dominio.Area;
import com.dcardozo.teburuportal.dominio.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {


    @Query("from Proyecto p inner join p.tablas t where t.area.id_area = ?1 group by p.id_proyecto")
    List<Proyecto> proyectosByArea(Integer id_area);

    @Query("from Proyecto p where p.id_proyecto = ?1")
    Optional<Proyecto> proyectoById(Integer id_proyecto);

    Proyecto findProyectoByNombre(String nombre);

}
