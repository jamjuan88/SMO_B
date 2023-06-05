package com.gestion.empleados.controller;

import com.gestion.empleados.models.Categoria;
import com.gestion.empleados.services.CategoriaService;
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
@RequestMapping("/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    //metodo para guardar una categoria
    @PostMapping
    public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.save(categoria), HttpStatus.CREATED);
    }
    
    //metodo para buscar un Categoria por id  
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorID(@PathVariable Long id){
        Categoria categoria = categoriaService.findById(id);                            
        return ResponseEntity.ok(categoria);
    }
    
    //Metodo para mostrar todas las Categoria
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias(){
        return new ResponseEntity<>(categoriaService.findAll(), HttpStatus.OK);
    }
    
    //metodo para actualizar datos de una Categoria
    @PutMapping("/{idCategoria}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long idCategoria,@RequestBody Categoria categoria){
        Categoria categoriaEncontrada = categoriaService.findById(idCategoria);
                            
        if(categoriaEncontrada == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            categoriaEncontrada.setNombreCategoria(categoria.getNombreCategoria());
            categoriaEncontrada.setDescripcionCategoria(categoria.getDescripcionCategoria());
                      
            return new ResponseEntity<>(categoriaService.save(categoriaEncontrada),HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //metodo sirve para eliminar una Categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id) {
        categoriaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
