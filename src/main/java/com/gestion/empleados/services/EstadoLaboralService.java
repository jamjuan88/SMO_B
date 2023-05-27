package com.gestion.empleados.services;

import com.gestion.empleados.models.EstadoLaboral;
import java.util.List;

public interface EstadoLaboralService {
    
    public EstadoLaboral save(EstadoLaboral estadoLaboral);
    
    public EstadoLaboral findById(Long id);
    
    public List<EstadoLaboral> findAll();
    
    public void delete(Long id);
    
}
