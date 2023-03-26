package com.dcardozo.teburuportal.controlador;

import com.dcardozo.teburuportal.dominio.Proyecto;
import com.dcardozo.teburuportal.servicio.ProyectoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin("http://localhost:4200") //para problemas de CORS
@RestController
@RequestMapping("/proyecto")
@Api(value = "Controlador de proyecto", tags = "Controlador de PROYECTO")
public class ProyectoController {

    private final ProyectoService service;
    @Autowired
    public ProyectoController(ProyectoService service) {
        this.service = service;
    }
    @GetMapping("/area/{id_area}")
    @ApiOperation(value = "Obtener todos los proyectos por id_area")
    public ResponseEntity<List<Proyecto>> getAllProyectosByArea(@PathVariable Integer id_area){
        return ResponseEntity.ok(service.getAllProyectosByIdArea(id_area));
    }

    @GetMapping("/{id_proyecto}")
    @ApiOperation(value = "Obtener proyecto por id_proyecto")
    public ResponseEntity<Proyecto> getProyectoById(@PathVariable Integer id_proyecto){
        return ResponseEntity.ok(service.buscarProyectoByIdServicio(id_proyecto));
    }

    @PostMapping
    @ApiOperation(value = "Crear proyecto")
    public ResponseEntity<Proyecto> postNuevoProyecto(@Valid @RequestBody Proyecto proyecto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearProyectoServicio(proyecto));
    }


}
