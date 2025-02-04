package com.ogl.mycash.service;

import com.ogl.mycash.model.Receita;
import com.ogl.mycash.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;

    public void salvar(Receita receita) {
        receitaRepository.save(receita);
    }
}
