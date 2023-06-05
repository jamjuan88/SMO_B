package com.gestion.empleados.controller;

import com.gestion.empleados.models.Aptitudes;
import com.gestion.empleados.services.AptitudesService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aptitudes")
@CrossOrigin(origins = "http://localhost:4200")
public class AptitudController {
    
    @Autowired
    private AptitudesService aptitudesService; 
    
    //metodo para guardar las aptitudes
    @PostMapping
    public ResponseEntity<Aptitudes> guardarAptitud(@RequestBody Aptitudes aptitudes) {
        return new ResponseEntity<>(aptitudesService.save(aptitudes), HttpStatus.CREATED);
    }
    
    //metodo para buscar un aptitud por id  
    @GetMapping("/{id}")
    public ResponseEntity<Aptitudes> obtenerAptitudPorID(@PathVariable Long id){
        Aptitudes aptitudes = aptitudesService.findById(id);                            
        return ResponseEntity.ok(aptitudes);
    }
    
    //Metodo para mostrar todas las aptitudes
    @GetMapping
    public ResponseEntity<List<Aptitudes>> listarAptitudes(){
        return new ResponseEntity<>(aptitudesService.findAll(), HttpStatus.OK);
    }
    
    //metodo para actualizar datos de una aptitud
    @PutMapping("/{idAptitud}")
    public ResponseEntity<Aptitudes> actualizarAptitud(@PathVariable Long idAptitud,@RequestBody Aptitudes aptitudes){
        Aptitudes aptitudEncontrada = aptitudesService.findById(idAptitud);
                            
        if(aptitudEncontrada == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            aptitudEncontrada.setNombreAptitud(aptitudes.getNombreAptitud());
            aptitudEncontrada.setDescripcionAptitud(aptitudes.getDescripcionAptitud());
                      
            return new ResponseEntity<>(aptitudesService.save(aptitudEncontrada),HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //metodo sirve para eliminar una aptitud
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAptitud(@PathVariable Long id) {
        aptitudesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
