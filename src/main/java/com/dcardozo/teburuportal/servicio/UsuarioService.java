package com.dcardozo.teburuportal.servicio;

import com.dcardozo.teburuportal.dominio.Usuario;
import com.dcardozo.teburuportal.exception.ErrorProcessException;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {
    Usuario logInUser(Usuario usuarioLogIn) throws ErrorProcessException;

    Usuario crearUsuario(Usuario usuario) throws ErrorProcessException;

}
