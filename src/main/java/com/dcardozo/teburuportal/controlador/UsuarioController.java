package com.dcardozo.teburuportal.controlador;

import com.dcardozo.teburuportal.dominio.Usuario;
import com.dcardozo.teburuportal.dto.UsuarioLogInDTO;
import com.dcardozo.teburuportal.dto.UsuarioSignUpDTO;
import com.dcardozo.teburuportal.dto.mapper.UsuarioMapper;
import com.dcardozo.teburuportal.servicio.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin("http://localhost:4200") //para problemas de CORS
@RestController
@RequestMapping("/usuario")
@Api(value = "Controlador de usuario", tags = "Controlador de USUARIO")
public class UsuarioController {

    Map<String, Object> response = new HashMap<>();
    private final UsuarioService service;
    private final UsuarioMapper usuarioMapper;
    @Autowired
    public UsuarioController(UsuarioService service,
                             UsuarioMapper usuarioMapper) {
        this.service = service;
        this.usuarioMapper = usuarioMapper;
    }

    @PostMapping("/login")
    @ApiOperation(value = "LogIn usuario")
    public ResponseEntity<?> logIn(@RequestBody UsuarioLogInDTO usuarioLogIn){
        Usuario usuario;
        try {
            usuario = service.logInUser(usuarioMapper.userLogindtoToEntity(usuarioLogIn));
            return ResponseEntity.status(HttpStatus.FOUND).body(usuarioMapper.entityToUsuarioPosLoginwithIdDTO(usuario));
        } catch (RuntimeException e) {
            response.put("MensajeError","Email o contraseña incorrecto, por favor intentente nuevamente.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/signUp")
    @ApiOperation(value = "SignUp usuario")
    public  ResponseEntity<?> signUp(@RequestBody UsuarioSignUpDTO usuarioSignUp) {
        Usuario usuario;
        try {
            usuario = service.crearUsuario(usuarioMapper.usuarioSignupDTOToEntity(usuarioSignUp));
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioMapper.entityToUsuarioLogInDTO(usuario));
        } catch (RuntimeException e) {
            this.response.put("Mensaje","Este usuario ya existe en la Base de Datos de Teburu Portal.");
            return new ResponseEntity<>(this.response, HttpStatus.FOUND) ;
        }
    }


}
