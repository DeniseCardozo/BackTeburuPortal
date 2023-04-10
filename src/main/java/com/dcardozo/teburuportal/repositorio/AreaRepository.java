package com.dcardozo.teburuportal.repositorio;

import com.dcardozo.teburuportal.dominio.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {

    @Query("from Area a where a.id_area = ?1")
    Optional<Area> areaById(Integer id_area);


    Area findAreaByNombre(String nombre);



}
