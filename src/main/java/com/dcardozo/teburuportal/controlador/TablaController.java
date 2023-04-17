package com.dcardozo.teburuportal.controlador;

import com.dcardozo.teburuportal.dominio.Tabla;
import com.dcardozo.teburuportal.dto.TablaCreatedOrUpdatedDTO;
import com.dcardozo.teburuportal.dto.TablaDTO;
import com.dcardozo.teburuportal.exception.ErrorProcessException;
import com.dcardozo.teburuportal.servicio.TablaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200") //para problemas de CORS
@RestController
@RequestMapping("/tabla")
@Api(value = "Controlador de tabla", tags = "Controlador de TABLA")
public class TablaController {
    Map<String, Object> response = new HashMap<>();
    private final TablaService service;

    @GetMapping("/proyecto/{id_proyecto}")
    @ApiOperation(value = "Obtener todas las tablas por id_proyecto")
    public ResponseEntity<List<Tabla>> getAllTablasByIdProyecto(@PathVariable Integer id_proyecto) throws ErrorProcessException {
        return ResponseEntity.ok(service.getAllTablasByIdProyecto(id_proyecto));
    }

    @GetMapping("/esquema/{id_tabla}")
    @ApiOperation(value = "Obtener esquema de tabla")
    public ResponseEntity<String> getEsquema(@PathVariable Integer id_tabla) throws ErrorProcessException {
        return ResponseEntity.ok(service.getEsquema(id_tabla));
    }

    @GetMapping("/esquemaProcesado/{id_tabla}")
    @ApiOperation(value = "Obtener esquema procesado de tabla")
    public ResponseEntity<List<String[]>> getEsquemaProcesado(@PathVariable Integer id_tabla) throws ErrorProcessException {
        return ResponseEntity.ok(service.getEsquemaProcesado(id_tabla));
    }

    @GetMapping("/{id_tabla}")
    @ApiOperation(value = "Obtener tabla por id_tabla")
    public ResponseEntity<TablaDTO> getTablaDetalle(@PathVariable Integer id_tabla) throws ErrorProcessException {
        return ResponseEntity.ok(service.getTablaById(id_tabla));
    }

    @PostMapping
    @ApiOperation(value = "Crear tabla")
    public ResponseEntity<TablaCreatedOrUpdatedDTO> postNuevaTabla(@Valid @RequestBody Tabla tabla) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearTablaServicio(tabla));
    }

    @PutMapping("/{id_tabla}")
    @ApiOperation(value = "Modificar tabla")
    public ResponseEntity<?> updateTablaDetalle(@PathVariable Integer id_tabla, @Valid @RequestBody Tabla tabla) throws ErrorProcessException {
        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(service.updateTabla(id_tabla, tabla));

        } catch (RuntimeException e) {
            response.put("MensajeError","Tabla de id " + id_tabla + " no existente, no se podrá actualizar.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id_tabla}")
    @ApiOperation(value = "Eliminar tabla")
    public ResponseEntity<?> deleteTabla(@PathVariable Integer id_tabla) throws ErrorProcessException {
        try {
            return service.deleteTablaById(id_tabla);
        } catch (RuntimeException e) {
            response.put("MensajeError","Tabla de id " + id_tabla + " no existente.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
