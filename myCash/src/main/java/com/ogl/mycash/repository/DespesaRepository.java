package com.ogl.mycash.repository;

import com.ogl.mycash.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByUsuarioId(Integer id);
}
