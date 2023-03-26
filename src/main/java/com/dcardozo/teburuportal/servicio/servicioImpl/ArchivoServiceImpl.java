package com.dcardozo.teburuportal.servicio.servicioImpl;

import com.dcardozo.teburuportal.dominio.Archivo;
import com.dcardozo.teburuportal.repositorio.ArchivoRepository;
import com.dcardozo.teburuportal.servicio.ArchivoService;
import com.dcardozo.teburuportal.servicio.TablaService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class ArchivoServiceImpl implements ArchivoService {
    private final ArchivoRepository repository;
    private final TablaService serviceTabla;
    InputStream inputStream;
    List<String[]> esquemaBaseDatos;
    String[] todosTiposDatos = {"bit","bool","tinyint","smallint","mediumint","bigint","int","float","double",
                            "varchar","tinytext","text","mediumtext","longtext","blob",
                                };
    Integer maxCantidadColumnas;
    String mensaje;

    @Autowired
    public ArchivoServiceImpl(TablaService serviceTabla,
                              ArchivoRepository repository) {
        this.serviceTabla = serviceTabla;
        this.repository = repository;
    }


    @Override
    public String uploadArchivo(MultipartFile multipartFiles, Integer id_tabla, Integer id_usuario) throws IOException {
        try {

            this.inputStream= multipartFiles.getInputStream();
            this.esquemaBaseDatos = serviceTabla.getEsquemaProcesado(id_tabla);

            // Obtener la instancia del libro de trabajo para el archivo XLSX
            Workbook workbook = WorkbookFactory.create(inputStream);

            // Obtener la primera hoja del libro
            Sheet sheet = workbook.getSheetAt(0);

            System.out.println("lectura de archivo...");

            //Iterar en cada fila de la hoja
            for (Row row : sheet) {

                short indx = 0;
                    // Iterar cada celda
                for (Cell cell : row) {

                    this.maxCantidadColumnas = this.esquemaBaseDatos.size();
                    while (indx < this.maxCantidadColumnas) {

                    // Iterar cada celda nombre de columna
                    if (cell.getRowIndex() == 0 ) {
//                        this.maxCantidadColumnas = this.esquemaBaseDatos.size();

                        if(row.getLastCellNum() == this.esquemaBaseDatos.size()) {
//                            System.out.println(cell.getStringCellValue());
//                            System.out.println(cell.getRow());
//                            System.out.println("valor "+ row.getCell(indx) + " "+cell.getColumnIndex());
//                        }

                            short indxFor = 0;
                            for (String[] elem : this.esquemaBaseDatos) {
                                String nombre = elem[0].toLowerCase();
//                                String valorCeldaLowerCase = String.valueOf(row.getCell(indx)).toLowerCase();

//                                System.out.println(valorCeldaLowerCase);
//                                System.out.println(row.getCell(indx));
//                                System.out.println(cell.getStringCellValue());

                                if (row.getCell(indxFor) == null) {
                                    System.out.println("entro al if que usa indx");
                                    System.out.println("indx igual a " + indx);
                                    System.out.println("entro al if por tener null "+ row.getCell(indx));
                                    return this.mensaje = "En el archivo ingresado existe una columna con nombre en NULL y esto no pertenece a la estructura de la tabla";
                                }

                                System.out.println(row.getCell(indxFor).getStringCellValue().toLowerCase());
                                System.out.println(cell.getColumnIndex());
                                System.out.println(this.esquemaBaseDatos.indexOf(elem));

                                if (this.esquemaBaseDatos.indexOf(elem) == indxFor && !row.getCell(indxFor).getStringCellValue().toLowerCase().equals(nombre)) {
                                    System.out.println("entro al if por mal nombre "+(cell.getStringCellValue()));
                                    System.out.println("entro al if por "+ row.getCell(cell.getColumnIndex()));
                                    System.out.println("columna nombre "+ nombre);
                                    System.out.println(this.esquemaBaseDatos.indexOf(elem));
                                    System.out.println(cell.getColumnIndex());

                                    return this.mensaje = "El archivo ingresado no tiene los mismos nombres de columnas que los establecidos en el schema, la columna '" + indxFor + "' no pertenece a la estructura de la tabla";
                                }
                                indxFor++;
                            }
                        }
                        else {
                            return this.mensaje = "El archivo ingresado no tiene la misma cantidad de columnas que las establecidas en el schema, columnas contadas: " + row.getLastCellNum() + ", esperadas: " + this.esquemaBaseDatos.size();
                        }
                    }

//                    while (indx < this.maxCantidadColumnas) {
//                        System.out.println(indx);

                    if (cell.getRowIndex() > 0) {

                        for (String[] elem : this.esquemaBaseDatos) {
                            String nombre = elem[0];
                            String tipo = elem[1];
                            String nullNotNull = elem[2].toLowerCase();

                            if (this.esquemaBaseDatos.indexOf(elem) == indx && nullNotNull.equals("notnull")) {
//                                System.out.println(nullNotNull);
                                if (row.getCell(indx) == null || row.getCell(indx).getCellType() == CellType.BLANK) {
//                                    System.out.println(indx + " - " + cell.getRowIndex());
//                                    System.out.println("valor "+row.getCell(indx));
                                    return this.mensaje = "La columna '" + nombre + "' es de tipo NOTNULL y existen datos que son NULL o BLANK en la fila: " + cell.getRowIndex();
                            }} else {
                                if (row.getCell(indx) == null || row.getCell(indx).getCellType() == CellType.BLANK) {
                                    continue;
                                }
                            }

                            if (this.esquemaBaseDatos.indexOf(elem) == indx) {
                                for (String tipoDato : this.todosTiposDatos) {
                                    if (tipo.contains(tipoDato)) {
                                        switch (tipoDato) {
                                            case "bit":
                                            case "bool":
                                            case "tinyint":
                                            case "smallint":
                                            case "mediumint":
                                            case "int":
                                            case "bigint":
                                            case "float":
                                            case "double":
                                                if (row.getCell(indx).getCellType() != CellType.NUMERIC) {
                                                    return this.mensaje = "La columna '"+ nombre +"' es del tipo de dato 'numérico' y existe un dato que no es de este mismo tipo en la fila: " + cell.getRowIndex() ;
                                                }
                                                break;
                                            case "tinytext":
                                            case "varchar":
                                            case "char":
                                            case "text":
                                            case "mediumtext":
                                            case "blob":
                                                if (row.getCell(indx).getCellType() != CellType.STRING) {
                                                    return this.mensaje = "La columna '"+ nombre +"' es de tipo de dato 'caracteres o cadenas de texto' y existe un dato que no es de este mismo tipo en la fila: " + cell.getRowIndex();
                                                }
                                                break;
                                            default:
                                                return (tipoDato + " tipo de dato no encontrado");
                    }}}}}}
                        indx++;
                }}
            }

            // Cerrar el file inputstream
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //ACA REUNIR LO NECESARIO PARA ENVIAR EL OBJETO ARCHIVO A LA BASE DE DATOS
        String nombreTabla = serviceTabla.getNombre(id_tabla);
        System.out.println(nombreTabla);

        Date fechaActual = new Date();

        Archivo archivo = new Archivo();

        archivo.setNombre_Tabla(nombreTabla);
        archivo.setUltima_actualizacion(fechaActual);
        archivo.setArchivo(multipartFiles.getBytes());
        archivo.setId_usuario(id_usuario);
        archivo.setId_tabla(id_tabla);

        repository.save(archivo);

        return this.mensaje = "Pasó todos los controles de estructura y calidad";

    }


}
