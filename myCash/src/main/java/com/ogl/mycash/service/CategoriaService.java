package com.ogl.mycash.service;

import com.ogl.mycash.model.Categoria;
import com.ogl.mycash.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public void salvar(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public List<Categoria> findByUsuarioId(Long idUsuario) {
        List<Categoria> categorias = categoriaRepository.findByUsuarioId(idUsuario);
        categorias.sort(Comparator.comparing(Categoria::getNome));
        return categorias;
    }

}
