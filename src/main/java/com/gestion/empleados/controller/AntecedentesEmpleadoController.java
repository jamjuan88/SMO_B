package com.gestion.empleados.controller;

import com.gestion.empleados.models.AntecedentesEmpleado;
import com.gestion.empleados.models.Empleado;
import com.gestion.empleados.services.AntecedentesEmpleadoService;
import com.gestion.empleados.services.EmpleadoService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

@RestController
@RequestMapping("/AntecedentesEmpleado")
@CrossOrigin(origins = "http://localhost:4200")
public class AntecedentesEmpleadoController {
    
    @Autowired
    private AntecedentesEmpleadoService antecedentesEmpleadoService;
    
    @Autowired
    private EmpleadoService empleadoService;
    
     //metodo para guardar los antecedentes
    @PostMapping
    public ResponseEntity<AntecedentesEmpleado> guardarAntecedentesEmpleado(@RequestBody AntecedentesEmpleado antecedentesEmpleado) {
        return new ResponseEntity<>(antecedentesEmpleadoService.save(antecedentesEmpleado), HttpStatus.CREATED);
    }
    
    //metodos para los archivos de los antecedentes medicos
    @PostMapping("/upload2")
    public ResponseEntity<String> guardarAntecedentesEmpleadoConArchivo(
    @RequestParam("archivo") MultipartFile archivo,
    @RequestParam("antecedentesId") Long idAntecedentes,
    @RequestParam("nombreArchivo") String nombreArchivo,
    @RequestParam("extensionArchivo") String extensionArchivo) {

    AntecedentesEmpleado antecedentesEmpleado = antecedentesEmpleadoService.findById(idAntecedentes);

    if (antecedentesEmpleado != null) {
        Empleado empleado = antecedentesEmpleado.getEmpleado();

        if (empleado != null) {
            Long empleadoId = empleado.getId();
            String nuevoNombreArchivo = nombreArchivo + "." + extensionArchivo;
            String rutaBase = "C:/Users/Usuario/Desktop/SistemaSMO/com.smo.spring/uploads/";
            String rutaEmpleado = rutaBase + empleadoId + "/antecedentesMedicos/";
            String rutaFalta = rutaEmpleado + idAntecedentes + "/";
            String rutaArchivo = rutaFalta + nuevoNombreArchivo;

            try {
                Files.createDirectories(Paths.get(rutaFalta));
                archivo.transferTo(new File(rutaArchivo));

                antecedentesEmpleado.setUrlArchivoExamen(rutaArchivo);
                antecedentesEmpleadoService.save(antecedentesEmpleado);

                return ResponseEntity.ok("Archivo subido correctamente");
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir el archivo");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el empleado asociado a los antecedentes");
        }
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron los antecedentes especificados");
    }
}
    
    //metodo para llamar los archivos desde el front
    @GetMapping("/archivosAntecedentes/{employeeId}/{idAntecedentes}/{nombreArch}")
    public ResponseEntity<Resource> getFile2(@PathVariable String employeeId, @PathVariable Number idAntecedentes ,@PathVariable String nombreArch) {
    File file = new File("C:/Users/Usuario/Desktop/SistemaSMO/com.smo.spring/uploads/" + employeeId + "/antecedentesMedicos/" + idAntecedentes + "/" + nombreArch);
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + nombreArch);
    if (nombreArch.endsWith(".pdf")) {
    headers.setContentType(MediaType.APPLICATION_PDF);
    } else if (nombreArch.endsWith(".jpg")) {
    headers.setContentType(MediaType.IMAGE_JPEG);
    }
    Resource resource = new FileSystemResource(file);
    return ResponseEntity.ok()
        .headers(headers)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(resource);
}



    
    //metodo para buscar un antecedente por id  
    @GetMapping("/{id}")
    public ResponseEntity<AntecedentesEmpleado> obtenerAntecedentesEmpleadoPorID(@PathVariable Long id){
        AntecedentesEmpleado antecedentesEmpleado = antecedentesEmpleadoService.findById(id);                            
        return ResponseEntity.ok(antecedentesEmpleado);
    }
    
    //Metodo para mostrar todos los antecedentes
    @GetMapping
    public ResponseEntity<List<AntecedentesEmpleado>> listarAntecedentesEmpleado(){
        return new ResponseEntity<>(antecedentesEmpleadoService.findAll(), HttpStatus.OK);
    }
    
    //metodo para buscar antecedentes por id del empleado
    @GetMapping("/empleado/{id}")
    public ResponseEntity<List<AntecedentesEmpleado>> obtenerAntecedentesPorEmpleado(@PathVariable Long id) {
    Empleado empleado = empleadoService.findById(id);
    if (empleado == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    List<AntecedentesEmpleado> antecedentesEmpleado = antecedentesEmpleadoService.findByEmpleado(empleado);
    return new ResponseEntity<>(antecedentesEmpleado, HttpStatus.OK);
    }
    
    //metodo para actualizar datos de los antecedentes del empleado
    @PutMapping("/{idAntecedentes}")
    public ResponseEntity<AntecedentesEmpleado> actualizarAntecedentesEmpleado(@PathVariable Long idAntecedentes,@RequestBody AntecedentesEmpleado antecedentesEmpleado){
        AntecedentesEmpleado AntecedentesEncontrados = antecedentesEmpleadoService.findById(idAntecedentes);
                            
        if(AntecedentesEncontrados == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            AntecedentesEncontrados.setAptitudes(antecedentesEmpleado.getAptitudes());
             AntecedentesEncontrados.setProveedoresAptitudes(antecedentesEmpleado.getProveedoresAptitudes());
              AntecedentesEncontrados.setMedicosProveedor(antecedentesEmpleado.getMedicosProveedor());
              AntecedentesEncontrados.setTipoPreexistencia(antecedentesEmpleado.getTipoPreexistencia()); 
              AntecedentesEncontrados.setPreexistencias(antecedentesEmpleado.getPreexistencias()); 
              AntecedentesEncontrados.setDescripcionPreexistencia(antecedentesEmpleado.getDescripcionPreexistencia()); 
             
                      
            return new ResponseEntity<>(antecedentesEmpleadoService.save(AntecedentesEncontrados),HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //metodo sirve para eliminar Antecedentes del empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAntecedentesEmpleado(@PathVariable Long id) {
        antecedentesEmpleadoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
