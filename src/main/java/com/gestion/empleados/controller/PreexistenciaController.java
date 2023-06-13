package com.gestion.empleados.controller;

import com.gestion.empleados.models.Preexistencias;
import com.gestion.empleados.services.PreexistenciaService;
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
@RequestMapping("/Preexistencia")
@CrossOrigin(origins = "http://localhost:4200")
public class PreexistenciaController {
    
    @Autowired
    private PreexistenciaService preexistenciaService;
    
    //metodo para guardar las aptitudes
    @PostMapping
    public ResponseEntity<Preexistencias> guardarPreexistencia(@RequestBody Preexistencias preexistencias) {
        return new ResponseEntity<>(preexistenciaService.save(preexistencias), HttpStatus.CREATED);
    }
    
    //metodo para buscar un aptitud por id  
    @GetMapping("/{id}")
    public ResponseEntity<Preexistencias> obtenerPreexistenciaPorId(@PathVariable Long id){
        Preexistencias preexistencias = preexistenciaService.findById(id);                            
        return ResponseEntity.ok(preexistencias);
    }
    
    //Metodo para mostrar todas las aptitudes
    @GetMapping
    public ResponseEntity<List<Preexistencias>> listarPreexistencias(){
        return new ResponseEntity<>(preexistenciaService.findAll(), HttpStatus.OK);
    }
    
    //metodo para actualizar datos de una aptitud
    @PutMapping("/{idPreexistencia}")
    public ResponseEntity<Preexistencias> actualizarPreexistencia(@PathVariable Long idPreexistencia,@RequestBody Preexistencias Preexistencias){
        Preexistencias preexistenciaEncontrada = preexistenciaService.findById(idPreexistencia);
                            
        if(preexistenciaEncontrada == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            preexistenciaEncontrada.setNombrePreexistencia(Preexistencias.getNombrePreexistencia());
            preexistenciaEncontrada.setDescripcionPreexistencia(Preexistencias.getDescripcionPreexistencia());
                      
            return new ResponseEntity<>(preexistenciaService.save(preexistenciaEncontrada),HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //metodo sirve para eliminar una aptitud
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPreexistencia(@PathVariable Long id) {
        preexistenciaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
