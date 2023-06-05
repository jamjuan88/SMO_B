package com.gestion.empleados.services;

import com.gestion.empleados.dao.AptitudDao;
import com.gestion.empleados.models.Aptitudes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AptitudesServiceImpl implements AptitudesService{
    
    @Autowired
    private AptitudDao aptitudDao; 

    @Override
    public Aptitudes save(Aptitudes aptitudes) {
      return aptitudDao.save(aptitudes);
    }

    @Override
    public Aptitudes findById(Long id) {
       return aptitudDao.findById(id).orElse(null);
    }

    @Override
    public List<Aptitudes> findAll() {
        return aptitudDao.findAll();
    }

    @Override
    public void delete(Long id) {
       aptitudDao.deleteById(id);
    }
    
    
    
}
