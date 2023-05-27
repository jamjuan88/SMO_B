package com.gestion.empleados.services;

import com.gestion.empleados.dao.EmpleadoDao;
import com.gestion.empleados.models.Empleado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    private EmpleadoDao empleadoDao;
    
    @Override
    public Empleado save(Empleado empleado) {
        return empleadoDao.save(empleado);
    }

    @Override
    public Empleado findById(Long id) {
         return empleadoDao.findById(id).orElse(null);
    }

    @Override
    public List<Empleado> findAll() {
        return empleadoDao.findAll();
    }

    @Override
    public void delete(Long id) {
         empleadoDao.deleteById(id);
    }

    
    
    
}
