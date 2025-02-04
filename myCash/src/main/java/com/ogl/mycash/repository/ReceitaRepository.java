package com.ogl.mycash.repository;

import com.ogl.mycash.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    Optional<Receita> findById(Long id);

    @Query(value = "SELECT SUM(CAST(r.valor AS float)) FROM Receita r WHERE r.usuario_id = :usuarioId", nativeQuery = true)
    Float findTotalValorByUsuarioId(Integer usuarioId);

    List<Receita> findByUsuarioId(Integer usuarioId);
}
