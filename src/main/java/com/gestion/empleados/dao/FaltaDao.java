package com.gestion.empleados.dao;
import com.gestion.empleados.models.Empleado;
import com.gestion.empleados.models.Falta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FaltaDao extends JpaRepository<Falta,Long>{

    public List<Falta> findByEmpleado(Empleado empleado);
    
   
    
}
