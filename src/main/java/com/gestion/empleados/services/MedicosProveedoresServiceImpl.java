package com.gestion.empleados.services;

import com.gestion.empleados.dao.MedicosProveedoresDao;
import com.gestion.empleados.models.MedicosProveedor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicosProveedoresServiceImpl implements MedicosProveedoresService{
    
    @Autowired
    private MedicosProveedoresDao medicosProveedoresDao; 

    @Override
    public MedicosProveedor save(MedicosProveedor medicosProveedor) {
        return medicosProveedoresDao.save(medicosProveedor);
    }

    @Override
    public MedicosProveedor findById(Long id) {
        return medicosProveedoresDao.findById(id).orElse(null);
    }

    @Override
    public List<MedicosProveedor> findAll() {
         return medicosProveedoresDao.findAll();
    }

    @Override
    public void delete(Long id) {
        medicosProveedoresDao.deleteById(id);
    }
    
}
