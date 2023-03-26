package com.dcardozo.teburuportal.repositorio;

import com.dcardozo.teburuportal.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("from Usuario u where u.email = ?1 and u.contrasena = ?2")
    Optional<Usuario> usuarioByEmailConstrasena(String email, String contrasena);

    @Query("from Usuario u where u.email = ?1")
    Optional<Usuario> usuarioByEmail(String email);
}
