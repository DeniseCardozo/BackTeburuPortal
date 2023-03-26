package com.dcardozo.teburuportal.servicio.servicioImpl;

import com.dcardozo.teburuportal.datos.DatosDummy;
import com.dcardozo.teburuportal.dominio.Usuario;
import com.dcardozo.teburuportal.repositorio.UsuarioRepository;
import com.dcardozo.teburuportal.servicio.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@SpringBootTest
class UsuarioServiceImplTest {
    @MockBean
    private UsuarioRepository repository;
    @Autowired
    private UsuarioService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("LogIn Usuario")
    void logInUser() {
        //given
        repository.save(DatosDummy.getUsuario1());
        repository.save(DatosDummy.getUsuario2());
        repository.save(DatosDummy.getUsuario3());
        Usuario usuario = DatosDummy.getUsuario1();
        String emailTest1 = "denise@gmail.com";
        String contrasenaTest1 = "q7wd8w77";
        when(repository.usuarioByEmailConstrasena(emailTest1, contrasenaTest1)).thenReturn(Optional.of(DatosDummy.getUsuario1()));

        //when
        Usuario usuarioLoguado = service.logInUser(usuario);

        //then
        assertThat(usuarioLoguado.getContrasena()).isEqualTo(contrasenaTest1);
        assertThat(usuarioLoguado.getEmail()).isEqualTo(emailTest1);

        verify(repository).usuarioByEmailConstrasena(anyString(), anyString());
    }

    @Test
    @DisplayName("Exception LogIn Usuario")
    void logInUsuarioWithException() {
        //given
        repository.save(DatosDummy.getUsuario2());
        repository.save(DatosDummy.getUsuario3());
        Usuario usuario = DatosDummy.getUsuario1();

        given(repository.usuarioByEmailConstrasena(usuario.getEmail(), usuario.getContrasena()))
                .willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> service.logInUser(usuario)).isInstanceOf(RuntimeException.class).hasMessageContaining("incorrectos");

        verify(repository).usuarioByEmailConstrasena(anyString(), anyString());
    }


        @Test
    @DisplayName("Crear Usuario")
    void crearUsuario() {
        //given
        Usuario usuario = DatosDummy.getUsuario1();

        //when
        service.crearUsuario(usuario);

        //then
        ArgumentCaptor<Usuario> usuarioArgumentCaptor = ArgumentCaptor.forClass(Usuario.class);
        verify(repository).save(usuarioArgumentCaptor.capture());
        Usuario usuarioCaptor = usuarioArgumentCaptor.getValue();
        assertThat(usuarioCaptor).isEqualTo(usuario);

        verify(repository).usuarioByEmail(anyString());
    }

    @Test
    @DisplayName("Exception Crear Usuario")
    void usuarioWithException() {
        //given
        Usuario usuario = DatosDummy.getUsuario1();

        given(repository.usuarioByEmail(usuario.getEmail()))
                .willReturn(Optional.of(usuario));

        //when
        //then
        assertThatThrownBy(() -> service.crearUsuario(usuario)).isInstanceOf(RuntimeException.class).hasMessageContaining("usuario existente");

        verify(repository).usuarioByEmail(anyString());
    }
}