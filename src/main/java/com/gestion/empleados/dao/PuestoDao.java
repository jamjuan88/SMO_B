
package com.gestion.empleados.dao;

import com.gestion.empleados.models.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PuestoDao extends JpaRepository<Puesto, Long>{
    
}
