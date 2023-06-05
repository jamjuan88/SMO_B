package com.gestion.empleados.services;

import com.gestion.empleados.models.Aptitudes;
import java.util.List;

public interface AptitudesService {
    
    public Aptitudes save(Aptitudes aptitudes);
    
    public Aptitudes findById(Long id);
    
    public List<Aptitudes> findAll();
    
    public void delete(Long id); 
    
}
