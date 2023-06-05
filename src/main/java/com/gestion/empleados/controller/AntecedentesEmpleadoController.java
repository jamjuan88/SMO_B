package com.gestion.empleados.controller;

import com.gestion.empleados.models.AntecedentesEmpleado;
import com.gestion.empleados.models.Empleado;
import com.gestion.empleados.services.AntecedentesEmpleadoService;
import com.gestion.empleados.services.EmpleadoService;
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
              AntecedentesEncontrados.setEmpleado(antecedentesEmpleado.getEmpleado());
              AntecedentesEncontrados.setMedicosProveedor(antecedentesEmpleado.getMedicosProveedor()); 
                      
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
