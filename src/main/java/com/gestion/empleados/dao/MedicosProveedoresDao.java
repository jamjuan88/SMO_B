package com.gestion.empleados.dao;

import com.gestion.empleados.models.MedicosProveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicosProveedoresDao extends JpaRepository<MedicosProveedor, Long>{
    
}
