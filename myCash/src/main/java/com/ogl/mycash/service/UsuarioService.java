package com.ogl.mycash.service;

import com.ogl.mycash.model.Usuario;
import com.ogl.mycash.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}
