package com.ogl.mycash.controller;

import com.ogl.mycash.model.Usuario;
import com.ogl.mycash.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/configuracoes")
public class ConfiguracoesController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/home-configuracoes")
    public String homeConfiguracoes(Model model) {
        model.addAttribute("usuario", usuarioService.getUsuarioLogado());
        model.addAttribute("moedaUsuario", usuarioService.getMoedaPrincipalByUsuario(usuarioService.getUsuarioLogado()));
        return "/configuracoes/home_configuracoes";
    }

    @PostMapping("/atualizar")
    public String atualizarConfiguracoes(@RequestParam(value = "nomeConfig", required = false) String nome,
                                                       @RequestParam(value = "emailConfig", required = false) String email,
                                                       @RequestParam(value = "senhaConfig", required = false) String senha,
                                                       @RequestParam(value = "moedaConfig", required = false) String moeda) {

            Usuario usuario = usuarioService.getUsuarioLogado();

            if (nome != null) usuario.setNome(nome);
            if (email != null) usuario.setEmail(email);
            if (senha != null) usuario.setSenha(passwordEncoder.encode(senha));
            if (moeda != null) usuario.setMoedaPreferida(moeda);

            usuarioService.salvar(usuario);
            return "redirect:/dashboard";
    }
}
