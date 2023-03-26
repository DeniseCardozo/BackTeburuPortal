package com.dcardozo.teburuportal.servicio.servicioImpl;

import com.dcardozo.teburuportal.dominio.Usuario;
import com.dcardozo.teburuportal.repositorio.UsuarioRepository;
import com.dcardozo.teburuportal.servicio.UsuarioService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario logInUser(Usuario usuarioLogIn) {
        Optional<Usuario> optionalUsuario = repository.usuarioByEmailConstrasena(usuarioLogIn.getEmail(), usuarioLogIn.getContrasena());
        if(optionalUsuario.isPresent()) {
            return optionalUsuario.get();
        } else {
            throw new RuntimeException("mail o password incorrectos");
        }
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        String email = usuario.getEmail();
        Optional<Usuario> optionalUsuario = repository.usuarioByEmail(email);
        if (optionalUsuario.isEmpty()) {
            usuario.setContrasena(RandomStringUtils.randomAlphanumeric(8));
            usuario.setRol(1);
            System.out.println("usuario nuevo -> " + usuario);
            return repository.save(usuario);
        } else  {
            throw new RuntimeException("usuario existente en base de datos");}
    }

}
