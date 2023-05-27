package com.gestion.empleados.dao;

import com.gestion.empleados.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpresaDao extends JpaRepository<Empresa, Long> {
    
}
