package com.gestion.empleados.services;

import com.gestion.empleados.dao.AntecedentesEmpleadoDao;
import com.gestion.empleados.models.AntecedentesEmpleado;
import com.gestion.empleados.models.Empleado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AntecedentesEmpleadoServiceImpl implements AntecedentesEmpleadoService{

    @Autowired
    private AntecedentesEmpleadoDao antecedentesEmpleadoDao;
    
    
    @Override
    public AntecedentesEmpleado save(AntecedentesEmpleado antecedentesEmpleado) {
         return antecedentesEmpleadoDao.save(antecedentesEmpleado);
    }

    @Override
    public AntecedentesEmpleado findById(Long id) {
        return antecedentesEmpleadoDao.findById(id).orElse(null);
    }

    @Override
    public List<AntecedentesEmpleado> findAll() {
        return antecedentesEmpleadoDao.findAll();
    }

    @Override
    public void delete(Long id) {
        antecedentesEmpleadoDao.deleteById(id);
    }

    @Override
    public List<AntecedentesEmpleado> findByEmpleado(Empleado empleado) {
       return antecedentesEmpleadoDao.findByEmpleado(empleado);
    }
    
    
    
}
