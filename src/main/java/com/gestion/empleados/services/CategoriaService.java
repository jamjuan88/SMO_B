package com.gestion.empleados.services;

import com.gestion.empleados.models.Categoria;
import java.util.List;


public interface CategoriaService {
    
    public Categoria save(Categoria categoria);
    
    public Categoria findById(Long id);
    
    public List<Categoria> findAll();
    
    public void delete(Long id);
    
    
}
