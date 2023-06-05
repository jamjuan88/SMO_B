
package com.gestion.empleados.services;
import com.gestion.empleados.models.AntecedentesEmpleado;
import com.gestion.empleados.models.Empleado;
import java.util.List;

public interface AntecedentesEmpleadoService {
    
    public AntecedentesEmpleado save(AntecedentesEmpleado antecedentesEmpleado);
    
    public AntecedentesEmpleado findById(Long id);
    
    public List<AntecedentesEmpleado> findAll();
    
    public void delete(Long id);
    
    public List<AntecedentesEmpleado> findByEmpleado(Empleado empleado);
    
}
