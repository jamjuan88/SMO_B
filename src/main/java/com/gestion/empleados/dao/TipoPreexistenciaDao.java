package com.gestion.empleados.dao;

import com.gestion.empleados.models.TipoPreexistencia;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TipoPreexistenciaDao extends JpaRepository<TipoPreexistencia, Long>{
    
}
