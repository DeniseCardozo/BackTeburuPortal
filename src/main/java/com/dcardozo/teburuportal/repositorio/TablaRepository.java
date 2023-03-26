package com.dcardozo.teburuportal.repositorio;

import com.dcardozo.teburuportal.dominio.Tabla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TablaRepository extends JpaRepository<Tabla, Integer> {

    @Query("from Tabla t inner join t.proyecto p where p.id_proyecto = ?1")
    List<Tabla> tablasByProyecto(Integer id_proyecto);

    @Query("select t.esquema from Tabla t where t.id_tabla = ?1")
    String esquemabyIdTabla(Integer id_tabla);

    @Query("select t.nombre from Tabla t where t.id_tabla = ?1")
    String nombrebyIdTabla(Integer id_tabla);

    @Query("from Tabla t where t.id_tabla = ?1")
    Tabla getTablaById(Integer idTabla);
}
