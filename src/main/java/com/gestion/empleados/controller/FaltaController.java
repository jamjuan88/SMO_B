package com.gestion.empleados.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.empleados.models.Empleado;
import com.gestion.empleados.models.Falta;
import com.gestion.empleados.services.EmpleadoService;
import com.gestion.empleados.services.FaltaService;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
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
    
    @PostMapping("/faltas")
  public ResponseEntity<String> guardarFaltaConArchivo(@RequestParam("file") MultipartFile file, @RequestParam("falta") Falta falta) {
    if (file.isEmpty()) {
      return ResponseEntity.badRequest().body("No se ha proporcionado ningún archivo");
    }

    try {
      String fileName = falta.getEmpleado() + "_" + file.getOriginalFilename();
      String filePath = "C:/Users/Usuario/Desktop/SistemaSMO/com.smo.spring/uploads/" + File.separator + fileName;
      File destFile = new File(filePath);

      // Guardar el archivo en la ubicación deseada
      file.transferTo(destFile);

      // Asignar la ruta del archivo a la falta
      falta.setUrlArchivo(filePath);

      // Guardar la falta en la base de datos utilizando tu servicio faltaService
      faltaService.save(falta);

      return ResponseEntity.ok("Falta y archivo guardados correctamente");
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el archivo");
    }
  }


    
    //Metodo para mostrar todas las faltas
    @GetMapping
    public ResponseEntity<List<Falta>> listarFaltas(){
        return new ResponseEntity<>(faltaService.findAll(), HttpStatus.OK);
    }
    
     //metodo para buscar un emppresa por id  
    @GetMapping("/{id}")
    public ResponseEntity<Falta> obtenerFaltaPorID(@PathVariable Long id){
        Falta falta = faltaService.findById(id);                            
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
    @PutMapping("/{id}")
    public ResponseEntity<Falta> actualizarFalta(@PathVariable Long id,@RequestBody Falta falta){
        Falta faltaEncontrada = faltaService.findById(id);
                            
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
