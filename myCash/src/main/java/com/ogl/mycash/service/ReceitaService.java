package com.ogl.mycash.service;

import com.ogl.mycash.model.Receita;
import com.ogl.mycash.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;

    public void salvar(Receita receita) {
        receitaRepository.save(receita);
    }

    public Float getTotalReceitasByUsuarioId(Integer usuarioId) {
        Float total = receitaRepository.findTotalValorByUsuarioId(usuarioId);
        return total != null ? total : (float) 0.0;
    }

    public List<Receita> findByUsuarioId(Integer usuarioId) {
        return receitaRepository.findByUsuarioId(usuarioId);
    }

    public void excluir(Long id) {
        receitaRepository.deleteById(id);
    }
}
