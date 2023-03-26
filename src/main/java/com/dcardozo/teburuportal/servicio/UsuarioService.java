package com.dcardozo.teburuportal.servicio;

import com.dcardozo.teburuportal.dominio.Usuario;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {
    Usuario logInUser(Usuario usuarioLogIn);

    Usuario crearUsuario(Usuario usuario);

}
