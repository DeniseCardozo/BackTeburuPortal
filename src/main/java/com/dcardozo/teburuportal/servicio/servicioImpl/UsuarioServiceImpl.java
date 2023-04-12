package com.dcardozo.teburuportal.servicio.servicioImpl;

import com.dcardozo.teburuportal.dominio.Usuario;
import com.dcardozo.teburuportal.exception.BadRequestException;
import com.dcardozo.teburuportal.exception.ErrorProcessException;
import com.dcardozo.teburuportal.exception.NotFoundException;
import com.dcardozo.teburuportal.repositorio.UsuarioRepository;
import com.dcardozo.teburuportal.servicio.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final String ERROR_NOT_FOUND = "During the process an error occurred: ";
    private final UsuarioRepository repository;

    @Override
    public Usuario logInUser(Usuario usuarioLogIn) throws ErrorProcessException {
        Usuario foundUsuario = (repository.usuarioByEmailConstrasena(usuarioLogIn.getEmail(), usuarioLogIn.getContrasena())).orElseThrow(() -> new NotFoundException("Email o contraseña incorrecto, por favor intentente nuevamente."));
        try {
            return foundUsuario;
        }catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) throws ErrorProcessException {
        if (repository.usuarioByEmail(usuario.getEmail()).isPresent()) {
            throw new BadRequestException("Este usuario ya existe en la Base de Datos de Teburu Portal.");
        }
        try {
            usuario.setContrasena(RandomStringUtils.randomAlphanumeric(8));
            usuario.setRol(1);
            return repository.save(usuario);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }
}
