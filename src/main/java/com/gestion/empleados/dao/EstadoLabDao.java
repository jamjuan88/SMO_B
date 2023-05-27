package com.gestion.empleados.dao;

import com.gestion.empleados.models.EstadoLaboral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoLabDao extends JpaRepository<EstadoLaboral, Long>{
    
}
