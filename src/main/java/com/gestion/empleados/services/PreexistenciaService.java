
package com.gestion.empleados.services;

import com.gestion.empleados.models.Preexistencias;
import java.util.List;


public interface PreexistenciaService {
    
    public Preexistencias save(Preexistencias preexistencias);
    
    public Preexistencias findById(Long id);
    
    public List<Preexistencias> findAll();
    
    public void delete(Long id); 
    
}
