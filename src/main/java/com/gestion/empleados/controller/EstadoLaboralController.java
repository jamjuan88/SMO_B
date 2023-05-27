package com.gestion.empleados.controller;

import com.gestion.empleados.models.EstadoLaboral;
import com.gestion.empleados.services.EstadoLaboralService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estadoLaboral")
@CrossOrigin(origins = "http://localhost:4200")
public class EstadoLaboralController {
    
    @Autowired
    private EstadoLaboralService estadoLaboralService;
    
    //Metodo para mostrar todos los Estados Laborales
    @GetMapping
    public ResponseEntity<List<EstadoLaboral>> listarEstadosLaborales(){
        return new ResponseEntity<>(estadoLaboralService.findAll(), HttpStatus.OK);
    }
    
    //metodo para crear Estados Laborales
    @PostMapping
    public ResponseEntity<EstadoLaboral> guardarEstadoLaboral(@RequestBody EstadoLaboral estadoLaboral) {
       return new ResponseEntity<>(estadoLaboralService.save(estadoLaboral),HttpStatus.CREATED);
    }
    
    //metodo para buscar Estado Laboral por ID  
    @GetMapping("/{id}")
    public ResponseEntity<EstadoLaboral> obtenerEstadoLaboralPorID(@PathVariable Long id){
        EstadoLaboral estadoLaboral = estadoLaboralService.findById(id);                            
        return ResponseEntity.ok(estadoLaboral);
    }
    
     //metodo para actualizar datos de un estado laboral
    @PutMapping("/{id}")
    public ResponseEntity<EstadoLaboral> actualizarEstadoLaboral(@PathVariable Long id,@RequestBody EstadoLaboral estadoLaboral){
        EstadoLaboral estadoEncontrado = estadoLaboralService.findById(id);
                            
        if(estadoEncontrado == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            estadoEncontrado.setNombreEstado(estadoLaboral.getNombreEstado());
            estadoEncontrado.setDescripcionEstado(estadoLaboral.getDescripcionEstado());
                                 
           
            return new ResponseEntity<>(estadoLaboralService.save(estadoEncontrado),HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
       
       //este metodo sirve para eliminar un empleado
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarEstadoLaboral(@PathVariable Long id){
		estadoLaboralService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
    }
        
    
    
}
