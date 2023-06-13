
package com.gestion.empleados.services;

import com.gestion.empleados.models.TipoPreexistencia;
import java.util.List;


public interface TipoPreexistenciaService {
    
     public TipoPreexistencia save(TipoPreexistencia tipoPreexistencia);
    
    public TipoPreexistencia findById(Long id);
    
    public List<TipoPreexistencia> findAll();
    
    public void delete(Long id); 
    
    
}
