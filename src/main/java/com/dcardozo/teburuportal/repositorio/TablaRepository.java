package com.dcardozo.teburuportal.repositorio;

import com.dcardozo.teburuportal.dominio.Tabla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TablaRepository extends JpaRepository<Tabla, Integer> {

    @Query("from Tabla t inner join t.proyecto p where p.id_proyecto = ?1")
    List<Tabla> tablasByProyecto(Integer id_proyecto);

    @Query("select t.esquema from Tabla t where t.id_tabla = ?1")
    Optional<String> esquemabyIdTabla(Integer id_tabla);

    @Query("select t.nombre from Tabla t where t.id_tabla = ?1")
    Optional<String> nombrebyIdTabla(Integer id_tabla);

    @Query("from Tabla t where t.id_tabla = ?1")
    Optional<Tabla> getTablaById(Integer idTabla);

    Tabla findTablaByNombre(String nombre);
}
