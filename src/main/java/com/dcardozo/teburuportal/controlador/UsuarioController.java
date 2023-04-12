package com.dcardozo.teburuportal.controlador;

import com.dcardozo.teburuportal.dominio.Usuario;
import com.dcardozo.teburuportal.dto.UsuarioLogInDTO;
import com.dcardozo.teburuportal.dto.UsuarioSignUpDTO;
import com.dcardozo.teburuportal.dto.mapper.UsuarioMapper;
import com.dcardozo.teburuportal.exception.ErrorProcessException;
import com.dcardozo.teburuportal.servicio.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200") //para problemas de CORS
@RestController
@RequestMapping("/usuario")
@Api(value = "Controlador de usuario", tags = "Controlador de USUARIO")
public class UsuarioController {
    private final UsuarioService service;
    private final UsuarioMapper usuarioMapper;

    @PostMapping("/login")
    @ApiOperation(value = "LogIn usuario")
    public ResponseEntity<?> logIn(@Valid @RequestBody UsuarioLogInDTO usuarioLogIn) throws ErrorProcessException {
        Usuario usuario = service.logInUser(usuarioMapper.userLogindtoToEntity(usuarioLogIn));
        return ResponseEntity.status(HttpStatus.FOUND).body(usuarioMapper.entityToUsuarioPosLoginwithIdDTO(usuario));
    }

    @PostMapping("/signUp")
    @ApiOperation(value = "SignUp usuario")
    public  ResponseEntity<?> signUp(@Valid @RequestBody UsuarioSignUpDTO usuarioSignUp) throws ErrorProcessException {
        Usuario usuario = service.crearUsuario(usuarioMapper.usuarioSignupDTOToEntity(usuarioSignUp));
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioMapper.entityToUsuarioLogInDTO(usuario));
    }
}
