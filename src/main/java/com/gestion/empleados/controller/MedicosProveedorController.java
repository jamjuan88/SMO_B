package com.gestion.empleados.controller;

import com.gestion.empleados.models.MedicosProveedor;
import com.gestion.empleados.services.MedicosProveedoresService;
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
@RequestMapping("/MedicosProveedores")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicosProveedorController {
    
    @Autowired
    private MedicosProveedoresService medicosProveedorService; 
    
     //metodo para guardar los medicos proveedores
    @PostMapping
    public ResponseEntity<MedicosProveedor> guardarMedicosProveedor(@RequestBody MedicosProveedor medicosProveedor) {
        return new ResponseEntity<>(medicosProveedorService.save(medicosProveedor), HttpStatus.CREATED);
    }
    
    //metodo para buscar un medico proveedor por id  
    @GetMapping("/{id}")
    public ResponseEntity<MedicosProveedor> obtenerMedicosProveedorPorID(@PathVariable Long id){
        MedicosProveedor medicosProveedor = medicosProveedorService.findById(id);                            
        return ResponseEntity.ok(medicosProveedor);
    }
    
    //Metodo para mostrar todos los medicos proveedores
    @GetMapping
    public ResponseEntity<List<MedicosProveedor>> listarMedicosProveedor(){
        return new ResponseEntity<>(medicosProveedorService.findAll(), HttpStatus.OK);
    }
    
    //metodo para actualizar datos de un medico proveedor
    @PutMapping("/{idMedicoProveedor}")
    public ResponseEntity<MedicosProveedor> actualizarMedicosProveedor(@PathVariable Long idMedicoProveedor,@RequestBody MedicosProveedor medicosProveedor){
        MedicosProveedor MedicoEncontrado = medicosProveedorService.findById(idMedicoProveedor);
                            
        if(MedicoEncontrado == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            MedicoEncontrado.setNombreMedicoProveedor(medicosProveedor.getNombreMedicoProveedor());
             MedicoEncontrado.setMatriculaMedicoProveedor(medicosProveedor.getMatriculaMedicoProveedor());
              MedicoEncontrado.setProveedoresAptitudes(medicosProveedor.getProveedoresAptitudes());
              
                      
            return new ResponseEntity<>(medicosProveedorService.save(MedicoEncontrado),HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //metodo sirve para eliminar un medico proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAptitud(@PathVariable Long id) {
        medicosProveedorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
