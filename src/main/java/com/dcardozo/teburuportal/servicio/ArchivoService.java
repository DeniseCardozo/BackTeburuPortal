package com.dcardozo.teburuportal.servicio;


import com.dcardozo.teburuportal.dominio.Archivo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface ArchivoService {
    String uploadArchivo(MultipartFile inputStream, Integer id_tabla, Integer id_usuario) throws IOException;

//    Archivo descargarArchivo (Integer id_archivo);
}
