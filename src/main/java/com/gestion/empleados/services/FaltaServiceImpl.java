package com.gestion.empleados.services;

import com.gestion.empleados.dao.FaltaDao;
import com.gestion.empleados.models.Empleado;
import com.gestion.empleados.models.Falta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaltaServiceImpl implements FaltaService{

    @Autowired
    private FaltaDao faltaDao;    
    
    @Override
    public Falta save(Falta falta) {
        return faltaDao.save(falta);
    }

    @Override
    public Falta findById(Long id) {
       return faltaDao.findById(id).orElse(null);
    }

    @Override
    public List<Falta> findAll() {
        return faltaDao.findAll();
    }

    @Override
    public void delete(Long id) {
         faltaDao.deleteById(id);
    }

    @Override
    public List<Falta> findByEmpleado(Empleado empleado) {
        return faltaDao.findByEmpleado(empleado);
    }

    
}
