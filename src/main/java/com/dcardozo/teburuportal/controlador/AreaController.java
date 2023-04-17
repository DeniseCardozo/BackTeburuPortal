package com.dcardozo.teburuportal.controlador;

import com.dcardozo.teburuportal.dominio.Area;
import com.dcardozo.teburuportal.dto.AreaDTO;
import com.dcardozo.teburuportal.exception.ErrorProcessException;
import com.dcardozo.teburuportal.servicio.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200") //para problemas de CORS
@RestController
@RequestMapping("/area")
@Api(value = "Controlador de area", tags = "Controlador de AREA")
public class AreaController {
    private final AreaService service;

    @GetMapping("")
    @ApiOperation(value = "Obtener todas las áreas")
    public ResponseEntity<List<Area>> getAllAreas() throws ErrorProcessException {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id_area}")
    @ApiOperation(value = "Obtener area por id_area")
    public ResponseEntity<AreaDTO> getAreaById(@PathVariable Integer id_area) throws ErrorProcessException {
        return ResponseEntity.ok(service.buscarByIdServicio(id_area));
    }

    @PostMapping
    @ApiOperation(value = "Crear area")
    public ResponseEntity<AreaDTO> postNuevaArea(@Valid @RequestBody Area area) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearAreaServicio(area));
    }

}
