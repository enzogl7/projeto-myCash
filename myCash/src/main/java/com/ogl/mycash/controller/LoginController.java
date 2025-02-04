package com.ogl.mycash.controller;

import com.ogl.mycash.model.Usuario;
import com.ogl.mycash.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "/login/landing_page";
    }

    @PostMapping("/registrar")
    public String registrar(@RequestParam String nome,
                            @RequestParam String email,
                            @RequestParam String senha,
                            @RequestParam String telefone,
                            @RequestParam String moedaPreferida,
                            RedirectAttributes redirectAttributes) {

        if (usuarioRepository.findByEmail(email).isPresent()) {
            redirectAttributes.addFlashAttribute("mensagem", "Email já cadastrado!");
            return "redirect:/login";

        }

        if(usuarioRepository.findByTelefone(telefone).isPresent()) {
            redirectAttributes.addFlashAttribute("mensagem", "Telefone já cadastrado!");
            return "redirect:/login";

        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setTelefone(telefone);
        usuario.setMoedaPreferida(moedaPreferida);

        usuarioRepository.save(usuario);

        redirectAttributes.addFlashAttribute("mensagemSucesso", "Usuário criado com sucesso!");
        return "redirect:/login";
    }
}
