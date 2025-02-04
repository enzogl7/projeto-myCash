package com.ogl.mycash.repository;

import com.ogl.mycash.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    Optional<Receita> findById(Long id);
}
