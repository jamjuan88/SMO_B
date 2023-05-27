
package com.gestion.empleados.services;

import com.gestion.empleados.dao.PuestoDao;
import com.gestion.empleados.models.Puesto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuestoServiceImpl implements PuestoService{

    @Autowired
    private PuestoDao puestoDao;
    
    @Override
    public Puesto save(Puesto puesto) {
        return puestoDao.save(puesto);
    }

    @Override
    public Puesto findById(Long id) {
       return puestoDao.findById(id).orElse(null);
    }

    @Override
    public List<Puesto> findAll() {
        return puestoDao.findAll();
    }

    @Override
    public void delete(Long id) {
        puestoDao.deleteById(id);
    }
    
}
