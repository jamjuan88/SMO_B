
package com.gestion.empleados.services;

import com.gestion.empleados.models.Empresa;
import java.util.List;


public interface EmpresaService {
    
    public Empresa save(Empresa empresa);
    
    public Empresa findById(Long id);
    
    public List<Empresa> findAll();
    
    public void delete(Long id);
    
}
