package com.gestion.empleados.controller;

import com.gestion.empleados.models.ProveedoresAptitudes;
import com.gestion.empleados.services.ProveedoresAptitudesService;
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
@RequestMapping("/ProveedoresAptitudes")
@CrossOrigin(origins = "http://localhost:4200")
public class ProveedorAptitudesController {
    
    @Autowired
    private ProveedoresAptitudesService ProveedoresAptitudService;
    
    //metodo para guardar los proveedores
    @PostMapping
    public ResponseEntity<ProveedoresAptitudes> guardarProveedorAptitud(@RequestBody ProveedoresAptitudes proveedoresAptitudes) {
        return new ResponseEntity<>(ProveedoresAptitudService.save(proveedoresAptitudes), HttpStatus.CREATED);
    }
    
    //metodo para buscar un proveedor por id  
    @GetMapping("/{id}")
    public ResponseEntity<ProveedoresAptitudes> obtenerProveedorAptitudPorID(@PathVariable Long id){
        ProveedoresAptitudes aptitudes = ProveedoresAptitudService.findById(id);                            
        return ResponseEntity.ok(aptitudes);
    }
    
    //Metodo para mostrar todos los proveedores
    @GetMapping
    public ResponseEntity<List<ProveedoresAptitudes>> listarProveedorAptitudes(){
        return new ResponseEntity<>(ProveedoresAptitudService.findAll(), HttpStatus.OK);
    }
    
    //metodo para actualizar datos de un proveedor
    @PutMapping("/{idProveedorAptitud}")
    public ResponseEntity<ProveedoresAptitudes> actualizarProveedorAptitud(@PathVariable Long idProveedorAptitud,@RequestBody ProveedoresAptitudes proveedoresAptitudes){
        ProveedoresAptitudes proveedorEncontrado = ProveedoresAptitudService.findById(idProveedorAptitud);
                            
        if(proveedorEncontrado == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            proveedorEncontrado.setNombreProveedorAptitud(proveedoresAptitudes.getNombreProveedorAptitud());
            proveedorEncontrado.setCuitProveedorAptitud(proveedoresAptitudes.getCuitProveedorAptitud());
            proveedorEncontrado.setLocalidadProveedorAptitud(proveedoresAptitudes.getLocalidadProveedorAptitud());
            proveedorEncontrado.setMedicosProveedor(proveedoresAptitudes.getMedicosProveedor());
                      
            return new ResponseEntity<>(ProveedoresAptitudService.save(proveedorEncontrado),HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //metodo sirve para eliminar un proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAptitud(@PathVariable Long id) {
        ProveedoresAptitudService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
