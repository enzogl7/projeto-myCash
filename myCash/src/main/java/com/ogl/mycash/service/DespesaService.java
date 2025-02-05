package com.ogl.mycash.service;

import com.ogl.mycash.model.Despesa;
import com.ogl.mycash.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {
    @Autowired
    private DespesaRepository despesaRepository;

    public List<Despesa> findByUsuarioId(Integer id) {
        return despesaRepository.findByUsuarioId(id);
    }

    public void salvar(Despesa despesa) {
        despesaRepository.save(despesa);
    }

    public void excluir(Long id) {
        despesaRepository.deleteById(id);
    }

    public Float getTotalDespesasByUsuarioId(Integer usuarioId) {
        Float total = despesaRepository.findTotalValorByUsuarioId(usuarioId);
        return total != null ? total : (float) 0.0;
    }

    public List<String> findIconesByUsuarioId(Integer usuarioId) {
        return despesaRepository.findIconesByUsuarioId(usuarioId);
    }

}
