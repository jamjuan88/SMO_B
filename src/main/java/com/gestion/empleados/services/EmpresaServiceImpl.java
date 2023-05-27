
package com.gestion.empleados.services;

import com.gestion.empleados.dao.EmpresaDao;
import com.gestion.empleados.models.Empresa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaDao empresaDao;
    
    @Override
    public List<Empresa> findAll() {
        return empresaDao.findAll();
    }
    
    @Override
    public Empresa save(Empresa empleado) {
        return empresaDao.save(empleado);
    }

    @Override
    public Empresa findById(Long id) {
         return empresaDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
         empresaDao.deleteById(id);
    }

    
}
