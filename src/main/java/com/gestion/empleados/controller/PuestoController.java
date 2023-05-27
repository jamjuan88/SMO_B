package com.gestion.empleados.controller;

import com.gestion.empleados.models.Puesto;
import com.gestion.empleados.services.PuestoService;
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
@RequestMapping("/puestos")
@CrossOrigin(origins = "http://localhost:4200")
public class PuestoController {
    
    @Autowired
    private PuestoService puestoService;
    
    //Metodo para mostrar todos los puestos
    @GetMapping
    public ResponseEntity<List<Puesto>> listarPuestos(){
        return new ResponseEntity<>(puestoService.findAll(), HttpStatus.OK);
    }    
     
    //metodo para crear puestos
    @PostMapping
    public ResponseEntity<Puesto> guardarPuestos(@RequestBody Puesto puesto) {
       return new ResponseEntity<>(puestoService.save(puesto),HttpStatus.CREATED);
    } 
   
    //metodo para buscar puesto por id  
    @GetMapping("/{id}")
    public ResponseEntity<Puesto> obtenerPuestoPorID(@PathVariable Long id){
        Puesto puesto = puestoService.findById(id);                            
        return ResponseEntity.ok(puesto);
    }
    
    //metodo para actualizar datos de un puesto
    @PutMapping("/{id}")
    public ResponseEntity<Puesto> actualizarPuesto(@PathVariable Long id,@RequestBody Puesto puesto){
        Puesto puestoEncontrado = puestoService.findById(id);
                            
        if(puestoEncontrado == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            puestoEncontrado.setNombrePuesto(puesto.getNombrePuesto());
            puestoEncontrado.setDepartamento(puesto.getDepartamento());
            puestoEncontrado.setDescripcionPuesto(puesto.getDescripcionPuesto());
            puestoEncontrado.setJornadaLaboralPuesto(puesto.getJornadaLaboralPuesto());            
           
            return new ResponseEntity<>(puestoService.save(puestoEncontrado),HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        
           
        //este metodo sirve para eliminar un empleado
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarPuesto(@PathVariable Long id){
		puestoService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
