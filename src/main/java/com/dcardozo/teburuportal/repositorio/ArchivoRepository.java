package com.dcardozo.teburuportal.repositorio;

import com.dcardozo.teburuportal.dominio.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Integer> {

}
