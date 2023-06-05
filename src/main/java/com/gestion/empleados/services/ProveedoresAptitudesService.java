
package com.gestion.empleados.services;

import com.gestion.empleados.models.ProveedoresAptitudes;
import java.util.List;

public interface ProveedoresAptitudesService {
    
     public ProveedoresAptitudes save(ProveedoresAptitudes proveedoresAptitudes);
    
    public ProveedoresAptitudes findById(Long id);
    
    public List<ProveedoresAptitudes> findAll();
    
    public void delete(Long id);    
    
    
}
