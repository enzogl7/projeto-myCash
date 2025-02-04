package com.ogl.mycash.repository;

import com.ogl.mycash.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findById(Long id);

    List<Categoria> findByUsuarioId(Long id);
}
