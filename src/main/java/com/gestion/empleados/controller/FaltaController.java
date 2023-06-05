package com.gestion.empleados.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.empleados.models.Empleado;
import com.gestion.empleados.models.Falta;
import com.gestion.empleados.services.EmpleadoService;
import com.gestion.empleados.services.FaltaService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import org.apache.commons.io.FilenameUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/faltas")
@CrossOrigin(origins = "http://localhost:4200")
public class FaltaController {
    
    @Autowired
    private FaltaService faltaService;
    
    @Autowired
    private EmpleadoService empleadoService;
    
    //metodo para guardar las faltas
    @PostMapping
    public ResponseEntity<Falta> guardarFalta(@RequestBody Falta falta) {
        return new ResponseEntity<>(faltaService.save(falta), HttpStatus.CREATED);
    }   
    
    @PostMapping("/upload2")
public ResponseEntity<String> guardarFaltaConArchivo(
        @RequestParam("archivo") MultipartFile archivo,
        @RequestParam("faltaId") Long idFalta,
        @RequestParam("nombreArchivo") String nombreArchivo,
        @RequestParam("extensionArchivo") String extensionArchivo) {

    // Obtener la falta correspondiente a faltaId desde la base de datos
    Falta falta = faltaService.findById(idFalta);

    // Obtener el ID del empleado a partir del ID de la falta
    Long empleadoId = falta.getEmpleado().getId();

      // Construir el nuevo nombre del archivo con la extensión
    String nuevoNombreArchivo = nombreArchivo + "." + extensionArchivo;

    // Construir la ruta de almacenamiento del archivo
    String rutaBase = "C:/Users/Usuario/Desktop/SistemaSMO/com.smo.spring/uploads/";
    String rutaEmpleado = rutaBase + empleadoId + "/";
    String rutaFalta = rutaEmpleado + idFalta + "/";
    String rutaArchivo = rutaFalta + nuevoNombreArchivo;

    try {
        // Crear las carpetas si no existen
        Files.createDirectories(Paths.get(rutaFalta));

        // Guardar el archivo en la ruta especificada
        archivo.transferTo(new File(rutaArchivo));

        // Almacenar la ruta del archivo en la falta correspondiente
        falta.setUrlArchivo(rutaArchivo);
        faltaService.save(falta);

        return ResponseEntity.ok("Archivo subido correctamente");
    } catch (IOException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir el archivo");
    }
}

   
    
    //Metodo para mostrar todas las faltas
    @GetMapping
    public ResponseEntity<List<Falta>> listarFaltas(){
        return new ResponseEntity<>(faltaService.findAll(), HttpStatus.OK);
    }
    
     //metodo para buscar un emppresa por id  
    @GetMapping("/{idFalta}")
    public ResponseEntity<Falta> obtenerFaltaPorID(@PathVariable Long idFalta){
        Falta falta = faltaService.findById(idFalta);                            
        return ResponseEntity.ok(falta);
    }
    
    @GetMapping("/empleado/{id}")
    public ResponseEntity<List<Falta>> obtenerFaltasPorEmpleado(@PathVariable Long id) {
    Empleado empleado = empleadoService.findById(id);
    if (empleado == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    List<Falta> faltas = faltaService.findByEmpleado(empleado);
    return new ResponseEntity<>(faltas, HttpStatus.OK);
    }

    //este metodo sirve para eliminar una falta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarFalta(@PathVariable Long id) {
        faltaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
        
          //metodo para actualizar datos de una falta
    @PutMapping("/{idFalta}")
    public ResponseEntity<Falta> actualizarFalta(@PathVariable Long idFalta,@RequestBody Falta falta){
        Falta faltaEncontrada = faltaService.findById(idFalta);
                            
        if(faltaEncontrada == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            faltaEncontrada.setFechaFaltaInicio(falta.getFechaFaltaInicio());
            faltaEncontrada.setFechaFaltaFinal(falta.getFechaFaltaFinal());
            faltaEncontrada.setDescripcionFalta(falta.getDescripcionFalta());
            faltaEncontrada.setTipoFalta(falta.getTipoFalta());
            faltaEncontrada.setEmpleado(falta.getEmpleado());
           
            return new ResponseEntity<>(faltaService.save(faltaEncontrada),HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        
}