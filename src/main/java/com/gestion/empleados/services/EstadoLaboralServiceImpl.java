package com.gestion.empleados.services;

import com.gestion.empleados.dao.EstadoLabDao;
import com.gestion.empleados.models.EstadoLaboral;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoLaboralServiceImpl implements EstadoLaboralService{

    @Autowired
    private EstadoLabDao estadoLaboralDao;
    
    @Override
    public EstadoLaboral save(EstadoLaboral estadoLaboral) {
        return estadoLaboralDao.save(estadoLaboral);
    }

    @Override
    public EstadoLaboral findById(Long id) {
       return estadoLaboralDao.findById(id).orElse(null);
    }

    @Override
    public List<EstadoLaboral> findAll() {
       return estadoLaboralDao.findAll();
    }

    @Override
    public void delete(Long id) {
       estadoLaboralDao.deleteById(id);
    }
    
}
