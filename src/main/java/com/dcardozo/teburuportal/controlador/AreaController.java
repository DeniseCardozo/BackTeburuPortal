package com.dcardozo.teburuportal.controlador;

import com.dcardozo.teburuportal.dominio.Area;
import com.dcardozo.teburuportal.servicio.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin("http://localhost:4200") //para problemas de CORS
@RestController
@RequestMapping("/area")
@Api(value = "Controlador de area", tags = "Controlador de AREA")
public class AreaController {

    private final AreaService service;
    @Autowired
    public AreaController(AreaService service) {
        this.service = service;
    }


    @GetMapping("")
    @ApiOperation(value = "Obtener todas las áreas")
    public ResponseEntity<List<Area>> getAllAreas(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id_area}")
    @ApiOperation(value = "Obtener area por id_area")
    public ResponseEntity<Area> getAreaById(@PathVariable Integer id_area){
        return ResponseEntity.ok(service.buscarByIdServicio(id_area));
    }

    @PostMapping
    @ApiOperation(value = "Crear area")
    public ResponseEntity<Area> postNuevaArea(@Valid @RequestBody Area area){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearAreaServicio(area));
    }

    @GetMapping("/name/{nombre}")
    @ApiIgnore
    public ResponseEntity<?> findNombre(@PathVariable String nombre){
        return service.findNombreArea(nombre);
    }

}
