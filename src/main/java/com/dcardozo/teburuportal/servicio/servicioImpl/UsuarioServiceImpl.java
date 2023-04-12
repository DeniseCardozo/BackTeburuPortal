package com.dcardozo.teburuportal.servicio.servicioImpl;

import com.dcardozo.teburuportal.dominio.Usuario;
import com.dcardozo.teburuportal.exception.BadRequestException;
import com.dcardozo.teburuportal.exception.ErrorProcessException;
import com.dcardozo.teburuportal.exception.NotFoundException;
import com.dcardozo.teburuportal.repositorio.UsuarioRepository;
import com.dcardozo.teburuportal.servicio.UsuarioService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final String ERROR_NOT_FOUND = "During the process an error occurred: ";
    private final UsuarioRepository repository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

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
        if (repository.usuarioByEmail(usuario.getEmail()) != null) {
            throw new BadRequestException("Este usuario ya existe en la Base de Datos de Teburu Portal.");
        }
        try {
            usuario.setContrasena(RandomStringUtils.randomAlphanumeric(8));
            usuario.setRol(1);
            return repository.save(usuario);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }

//        String email = usuario.getEmail();
//        Optional<Usuario> optionalUsuario = repository.usuarioByEmail(email);
//        if (optionalUsuario.isEmpty()) {
//            usuario.setContrasena(RandomStringUtils.randomAlphanumeric(8));
//            usuario.setRol(1);
//            System.out.println("usuario nuevo -> " + usuario);
//            return repository.save(usuario);
//        } else  {
//            throw new RuntimeException("usuario existente en base de datos");}
    }

}
