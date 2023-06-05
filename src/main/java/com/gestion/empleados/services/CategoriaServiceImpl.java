package com.gestion.empleados.services;

import com.gestion.empleados.dao.CategoriaDao;
import com.gestion.empleados.models.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService{
    
    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaDao.save(categoria);
    }

    @Override
    public Categoria findById(Long id) {
        return categoriaDao.findById(id).orElse(null);
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaDao.findAll();
    }

    @Override
    public void delete(Long id) {
        categoriaDao.deleteById(id);
    }
    
}
