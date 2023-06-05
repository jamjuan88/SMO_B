package com.gestion.empleados.dao;

import com.gestion.empleados.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaDao extends JpaRepository<Categoria, Long>{
    
}
