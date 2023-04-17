package com.dcardozo.teburuportal.servicio.servicioImpl;

import com.dcardozo.teburuportal.dominio.Archivo;
import com.dcardozo.teburuportal.exception.ErrorProcessException;
import com.dcardozo.teburuportal.repositorio.ArchivoRepository;
import com.dcardozo.teburuportal.servicio.ArchivoService;
import com.dcardozo.teburuportal.servicio.TablaService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArchivoServiceImpl implements ArchivoService {
    private final ArchivoRepository repository;
    private final TablaService serviceTabla;
    InputStream inputStream;
    List<String[]> esquemaBaseDatos;
    String[] todosTiposDatos = {"bit","bool","tinyint","smallint","mediumint","bigint","int","float","double"};
    Integer maxCantidadColumnas;
    String mensaje;
    String nombreColumna;

    @Override
    public String uploadArchivo(MultipartFile multipartFiles, Integer id_tabla, Integer id_usuario) throws ErrorProcessException {
        try {
            inputStream= multipartFiles.getInputStream();
            esquemaBaseDatos = serviceTabla.getEsquemaProcesado(id_tabla);

            return readAndCheckFile(inputStream, multipartFiles, id_tabla, id_usuario);

        } catch (RuntimeException | IOException e) {
            throw new ErrorProcessException(e.getMessage());
        }
    }

    public String readAndCheckFile (InputStream inputStream, MultipartFile multipartFiles, Integer id_tabla, Integer id_usuario) throws IOException, ErrorProcessException {
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        System.out.println("lectura de archivo...");

        for (Row row : sheet) {
            short indx = 0;

            for (Cell cell : row) {
                maxCantidadColumnas = esquemaBaseDatos.size();
                while (indx < maxCantidadColumnas) {

                    if (cell.getRowIndex() == 0 ) {
                        switch (checkColumnNames(row, cell)) {
                            case "nameNull":
                                return mensaje = "En el archivo ingresado existe una columna con nombre en NULL y esto no pertenece a la estructura de la tabla";
                            case "differentNames":
                                return mensaje = "El archivo ingresado no tiene los mismos nombres de columnas que los establecidos en el schema, la columna '" + (cell.getColumnIndex() + 1) + "' no pertenece a la estructura de la tabla";
                            case "differentNumberOfColumns":
                                return mensaje = "El archivo ingresado no tiene la misma cantidad de columnas que las establecidas en el schema, columnas contadas: " + row.getLastCellNum() + ", esperadas: " + esquemaBaseDatos.size();
                            default:
                                break;
                        }
                    }
                    if (cell.getRowIndex() > 0) {
                        switch (checkCellsWithData (row, indx)) {
                            case "nullData":
                                return mensaje = "La columna '" + nombreColumna + "' es de tipo NOTNULL y existen datos que son NULL o BLANK en la fila: " + (cell.getRowIndex() + 1);
                            case "checkDataTypeNumeric":
                                return mensaje = "La columna '"+ nombreColumna +"' es del tipo de dato 'numérico' y existe un dato que no es de este mismo tipo en la fila: " + (cell.getRowIndex() + 1);
                            default:
                                break;
                        }
                    }
                    indx++;
                }
                if (indx == maxCantidadColumnas) {
                    indx = 0;
                }
            }
        }
        inputStream.close();
        return fileReadyToSave(multipartFiles, id_tabla, id_usuario);
    }

    public String checkColumnNames (Row row, Cell cell) {
        if(row.getLastCellNum() == esquemaBaseDatos.size()) {
            short indxFor = 0;
            for (String[] elem : esquemaBaseDatos) {
                String nombre = elem[0].toLowerCase();

                if (indxFor == cell.getColumnIndex()){
                    if (row.getCell(indxFor) == null || cell.getCellType() == CellType.BLANK) {
                        return "nameNull";
                    }
                    if (esquemaBaseDatos.indexOf(elem) == indxFor && !row.getCell(indxFor).getStringCellValue().toLowerCase().equals(nombre)) {
                        return "differentNames";
                    }
                }
                indxFor++;
            }
        } else {
            return "differentNumberOfColumns";
        }
        return "ok";
    }

    public String checkCellsWithData (Row row, short indx) {
        for (String[] elem : esquemaBaseDatos) {
            nombreColumna = elem[0];
            String tipo = elem[1];
            String nullNotNull = elem[2].toLowerCase();

            if(esquemaBaseDatos.indexOf(elem) == indx) {
                if (row.getCell(indx) == null || row.getCell(indx).getCellType() == CellType.BLANK) {
                    if ( nullNotNull.equals("notnull")) {
                        return "nullData";
                    }
                } else {
                    for (String tipoDato : this.todosTiposDatos) {
                        if (tipo.contains(tipoDato) && row.getCell(indx).getCellType() != CellType.NUMERIC) {
                            return "checkDataTypeNumeric";
                        }
                    }
                }
            }
        }
        return "ok";
    }

    public String fileReadyToSave (MultipartFile multipartFiles, Integer id_tabla, Integer id_usuario) throws ErrorProcessException, IOException {
        Archivo archivo = new Archivo();

        archivo.setNombre_Tabla(serviceTabla.getNombre(id_tabla));
        archivo.setUltima_actualizacion(new Date());
        archivo.setArchivo(multipartFiles.getBytes());
        archivo.setId_usuario(id_usuario);
        archivo.setId_tabla(id_tabla);

        repository.save(archivo);
        System.out.println("subió el archivo...");

        return mensaje = "Pasó todos los controles de estructura y calidad";

    }
 }

