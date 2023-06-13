package com.gestion.empleados.controller;

import com.gestion.empleados.models.TipoPreexistencia;
import com.gestion.empleados.services.TipoPreexistenciaService;
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
@RequestMapping("/TipoPreexistencia")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoPreexistenciaController {
    
    @Autowired
    private TipoPreexistenciaService tipoPreexistenciaService;
    
    //metodo para guardar las aptitudes
    @PostMapping
    public ResponseEntity<TipoPreexistencia> guardarTipoPreexistencia(@RequestBody TipoPreexistencia tipoPreexistencia) {
        return new ResponseEntity<>(tipoPreexistenciaService.save(tipoPreexistencia), HttpStatus.CREATED);
    }
    
    //metodo para buscar un aptitud por id  
    @GetMapping("/{id}")
    public ResponseEntity<TipoPreexistencia> obtenerTipoPreexistenciaPorId(@PathVariable Long id){
        TipoPreexistencia tipoPreexistencia = tipoPreexistenciaService.findById(id);                            
        return ResponseEntity.ok(tipoPreexistencia);
    }
    
    //Metodo para mostrar todas las aptitudes
    @GetMapping
    public ResponseEntity<List<TipoPreexistencia>> listarTipoPreexistencia(){
        return new ResponseEntity<>(tipoPreexistenciaService.findAll(), HttpStatus.OK);
    }
    
    //metodo para actualizar datos de una aptitud
    @PutMapping("/{idTipoPreexistencia}")
    public ResponseEntity<TipoPreexistencia> actualizarTipoPreexistencia(@PathVariable Long idTipoPreexistencia,@RequestBody TipoPreexistencia tipoPreexistencia){
        TipoPreexistencia TipoPreexistenciaEncontrada = tipoPreexistenciaService.findById(idTipoPreexistencia);
                            
        if(TipoPreexistenciaEncontrada == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            TipoPreexistenciaEncontrada.setNombreTipoPreexistencia(tipoPreexistencia.getNombreTipoPreexistencia());
            TipoPreexistenciaEncontrada.setDescripcionTipoPreexistencia(tipoPreexistencia.getDescripcionTipoPreexistencia());
                      
            return new ResponseEntity<>(tipoPreexistenciaService.save(TipoPreexistenciaEncontrada),HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //metodo sirve para eliminar una aptitud
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTipoPreexistencia(@PathVariable Long id) {
        tipoPreexistenciaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
