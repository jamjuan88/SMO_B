
package com.gestion.empleados.controller;

import com.gestion.empleados.models.Empresa;
import com.gestion.empleados.services.EmpresaService;
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
@RequestMapping("/empresas")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpresaController {
    
    @Autowired
    private EmpresaService empresaService;
    
     //Metodo para mostrar todos las empresas
    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas(){
        return new ResponseEntity<>(empresaService.findAll(), HttpStatus.OK);
    }    
     
    //metodo para crear Empresas
    @PostMapping
    public ResponseEntity<Empresa> guardarEmpresa(@RequestBody Empresa empresa) {
       return new ResponseEntity<>(empresaService.save(empresa),HttpStatus.CREATED);
    } 
   
     //metodo para buscar un emppresa por id  
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obtenerEmpresaPorID(@PathVariable Long id){
        Empresa empresa = empresaService.findById(id);                            
        return ResponseEntity.ok(empresa);
    }
    
    //metodo para actualizar datos de una empresa
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> actualizarEmpresa(@PathVariable Long id,@RequestBody Empresa empresa){
        Empresa empresaEncontrada = empresaService.findById(id);
                            
        if(empresaEncontrada == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            empresaEncontrada.setNombreEmpresa(empresa.getNombreEmpresa());
            empresaEncontrada.setRazonSocial(empresa.getRazonSocial());
            empresaEncontrada.setCuitEmpresa(empresa.getCuitEmpresa());
           
            return new ResponseEntity<>(empresaService.save(empresaEncontrada),HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        
           
        //este metodo sirve para eliminar un empleado
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarEmpresa(@PathVariable Long id){
		empresaService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
