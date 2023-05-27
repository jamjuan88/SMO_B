package com.gestion.empleados.dao;

import com.gestion.empleados.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpleadoDao extends JpaRepository<Empleado, Long> {
    
}
