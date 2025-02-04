package com.ogl.mycash.service;

import com.ogl.mycash.model.Categoria;
import com.ogl.mycash.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public void salvar(Categoria categoria) {
        categoriaRepository.save(categoria);
    }
}
