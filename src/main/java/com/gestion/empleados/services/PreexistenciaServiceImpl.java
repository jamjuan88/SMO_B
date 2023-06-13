package com.gestion.empleados.services;

import com.gestion.empleados.dao.PreexistenciaDao;
import com.gestion.empleados.models.Preexistencias;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreexistenciaServiceImpl implements PreexistenciaService {
    
    @Autowired
    private PreexistenciaDao preexistenciaDao;

    @Override
    public Preexistencias save(Preexistencias preexistencias) {
        return preexistenciaDao.save(preexistencias);
    }

    @Override
    public Preexistencias findById(Long id) {
        return preexistenciaDao.findById(id).orElse(null);
    }

    @Override
    public List<Preexistencias> findAll() {
        return preexistenciaDao.findAll();
    }

    @Override
    public void delete(Long id) {
       preexistenciaDao.deleteById(id);
    }
    
}
