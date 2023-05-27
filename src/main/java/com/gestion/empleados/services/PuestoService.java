
package com.gestion.empleados.services;

import com.gestion.empleados.models.Puesto;
import java.util.List;


public interface PuestoService {
    
    public Puesto save(Puesto puesto);
    
    public Puesto findById(Long id);
    
    public List<Puesto> findAll();
    
    public void delete(Long id);
}
