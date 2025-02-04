package com.ogl.mycash.service;

import com.ogl.mycash.infra.AuthenticationFacade;
import com.ogl.mycash.model.Usuario;
import com.ogl.mycash.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    public void salvar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario getUsuarioLogado() {
        String emailUsuario = authenticationFacade.getAuthentication().getName();
        return usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + emailUsuario));
    }

    public String getMoedaPrincipalByUsuario(Usuario usuario) {
        return usuario.getMoedaPreferida();
    }
}
