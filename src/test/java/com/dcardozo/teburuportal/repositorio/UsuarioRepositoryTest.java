package com.dcardozo.teburuportal.repositorio;

import com.dcardozo.teburuportal.datos.DatosDummy;
import com.dcardozo.teburuportal.dominio.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    private final UsuarioRepository repository;
    @Autowired
    public UsuarioRepositoryTest(UsuarioRepository repository) {
        this.repository = repository;
    }

    @BeforeEach
    void setUp() {
        //given => armar contexto - dar contexto
        repository.save(DatosDummy.getUsuario1());
        repository.save(DatosDummy.getUsuario2());
        repository.save(DatosDummy.getUsuario3());
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void usuarioByEmailConstrasena() {
        String emailTest1 = "denise@gmail.com";
        String contrasenaTest1 = "q7wd8w77";
        String emailTest2 = "tamara@gmail.com";
        String contrasenaTest2 = "aaaaaaaa";
        String emailTest3 = "matias@gmail.com";
        String contrasenaTest3 = "dfg57d45";

        //when => lo que quiero probar cuando pase algo
        Optional<Usuario> optionalUsuario1 = repository.usuarioByEmailConstrasena(emailTest1, contrasenaTest1);
        Optional<Usuario> optionalUsuario2 = repository.usuarioByEmailConstrasena(emailTest2, contrasenaTest2);
        Optional<Usuario> optionalUsuario3 = repository.usuarioByEmailConstrasena(emailTest3, contrasenaTest3);


        //then => validar que lo que está pasando devuelva lo esperado
        assertThat(optionalUsuario1.isPresent()).isTrue();
        assertThat(optionalUsuario2.isPresent()).isFalse();
        assertThat(optionalUsuario3.isPresent()).isFalse();

        assertThat(optionalUsuario1.get().getEmail()).isEqualTo(emailTest1);
        assertThat(optionalUsuario1.get().getContrasena()).isEqualTo(contrasenaTest1);

    }

    @Test
    void usuarioByEmail() {
        String emailTest1 = "denise@gmail.com";
        String emailTest2 = "matias@gmail.com";

        Optional<Usuario> optionalUsuario1 = repository.usuarioByEmail(emailTest1);
        Optional<Usuario> optionalUsuario2 = repository.usuarioByEmail(emailTest2);

        assertThat(optionalUsuario1.isPresent()).isTrue();
        assertThat(optionalUsuario2.isPresent()).isFalse();

        assertThat(optionalUsuario1.get().getEmail()).isEqualTo(emailTest1);

    }
}