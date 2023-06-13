package com.gestion.empleados.dao;

import com.gestion.empleados.models.Preexistencias;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PreexistenciaDao extends JpaRepository<Preexistencias, Long>{
    
}
