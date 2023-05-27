
package com.gestion.empleados.services;
import com.gestion.empleados.models.Empleado;
import com.gestion.empleados.models.Falta;
import java.util.List;

public interface FaltaService {
    
    public Falta save(Falta falta);
    
    public Falta findById(Long id);
    
    public List<Falta> findAll();
    
    public void delete(Long id); 
    
    public List<Falta> findByEmpleado(Empleado empleado);

}
