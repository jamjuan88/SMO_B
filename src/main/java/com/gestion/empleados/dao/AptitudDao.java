
package com.gestion.empleados.dao;

import com.gestion.empleados.models.Aptitudes;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AptitudDao extends JpaRepository<Aptitudes, Long>{
    
}
