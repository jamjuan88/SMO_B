package com.gestion.empleados.services;

import com.gestion.empleados.models.Empleado;
import java.util.List;


public interface EmpleadoService {
    
    public Empleado save(Empleado empleado);
    
    public Empleado findById(Long id);
    
    public List<Empleado> findAll();
    
    public void delete(Long id);    
    
    
}
