package com.gestion.empleados.dao;

import com.gestion.empleados.models.AntecedentesEmpleado;
import com.gestion.empleados.models.Empleado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntecedentesEmpleadoDao extends JpaRepository<AntecedentesEmpleado, Long>{

public List<AntecedentesEmpleado> findByEmpleado(Empleado empleado);
    
}
