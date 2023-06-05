package com.gestion.empleados.services;

import com.gestion.empleados.dao.ProveedoresAptitudesDao;
import com.gestion.empleados.models.ProveedoresAptitudes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedoresAptitudesServiceImpl implements ProveedoresAptitudesService{
    
    @Autowired
    private ProveedoresAptitudesDao proveedoresAptitudesDao; 

    @Override
    public ProveedoresAptitudes save(ProveedoresAptitudes proveedoresAptitudes) {
       return proveedoresAptitudesDao.save(proveedoresAptitudes);
    }

    @Override
    public ProveedoresAptitudes findById(Long id) {
        return proveedoresAptitudesDao.findById(id).orElse(null);
    }

    @Override
    public List<ProveedoresAptitudes> findAll() {
       return proveedoresAptitudesDao.findAll();
    }

    @Override
    public void delete(Long id) {
        proveedoresAptitudesDao.deleteById(id);
    }
    
}
