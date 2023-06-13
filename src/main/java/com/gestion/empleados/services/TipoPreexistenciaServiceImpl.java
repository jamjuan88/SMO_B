package com.gestion.empleados.services;

import com.gestion.empleados.dao.TipoPreexistenciaDao;
import com.gestion.empleados.models.TipoPreexistencia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoPreexistenciaServiceImpl implements TipoPreexistenciaService{
    
    @Autowired
    private TipoPreexistenciaDao tipoPreexistenciaDao;

    @Override
    public TipoPreexistencia save(TipoPreexistencia tipoPreexistencia) {
        return tipoPreexistenciaDao.save(tipoPreexistencia);
    }

    @Override
    public TipoPreexistencia findById(Long id) {
        return tipoPreexistenciaDao.findById(id).orElse(null);
    }

    @Override
    public List<TipoPreexistencia> findAll() {
        return tipoPreexistenciaDao.findAll();
    }

    @Override
    public void delete(Long id) {
        tipoPreexistenciaDao.deleteById(id);
    }
    
    
}
