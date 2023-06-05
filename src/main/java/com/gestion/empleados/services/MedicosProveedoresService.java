
package com.gestion.empleados.services;

import com.gestion.empleados.models.MedicosProveedor;
import java.util.List;

public interface MedicosProveedoresService {
    
    public MedicosProveedor save(MedicosProveedor medicosProveedor);
    
    public MedicosProveedor findById(Long id);
    
    public List<MedicosProveedor> findAll();
    
    public void delete(Long id); 
    
}
