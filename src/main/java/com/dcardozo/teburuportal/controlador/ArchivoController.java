package com.dcardozo.teburuportal.controlador;

import com.dcardozo.teburuportal.exception.ErrorProcessException;
import com.dcardozo.teburuportal.servicio.ArchivoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@CrossOrigin("http://localhost:4200") //para problemas de CORS
@RestController
@RequestMapping("/file")
@Api(value = "Controlador de archivo", tags = "Controlador de ARCHIVO")
public class ArchivoController {

    private final ArchivoService service;
    @Autowired
    public ArchivoController(ArchivoService service) {
        this.service = service;
    }

    @PostMapping("/upload/{id_tabla}/{id_usuario}")
    @ApiOperation(value = "Subir archivo, analizarlo y guardarlo")
    public String uploadFiles(@RequestParam("files") MultipartFile multipartFiles, @PathVariable Integer id_tabla, @PathVariable Integer id_usuario) throws IOException, ErrorProcessException {
//        return service.uploadArchivo(multipartFiles.getInputStream(), id_tabla);
        return service.uploadArchivo(multipartFiles, id_tabla, id_usuario);

    }

}
