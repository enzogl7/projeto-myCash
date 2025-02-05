package com.ogl.mycash.repository;

import com.ogl.mycash.model.Despesa;
import com.ogl.mycash.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByUsuarioId(Integer id);

    @Query(value = "SELECT SUM(CAST(d.valor AS float)) FROM Despesa d WHERE d.usuario_id = :usuarioId", nativeQuery = true)
    Float findTotalValorByUsuarioId(Integer usuarioId);

    List<Despesa> usuario(Usuario usuario);

    @Query("SELECT DISTINCT d.logoPath FROM Despesa d WHERE d.usuario.id = :usuarioId AND d.logoPath IS NOT NULL")
    List<String> findIconesByUsuarioId(@Param("usuarioId") Integer usuarioId);
}
