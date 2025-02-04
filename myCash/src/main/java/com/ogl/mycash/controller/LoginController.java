package com.ogl.mycash.controller;

import com.ogl.mycash.model.Usuario;
import com.ogl.mycash.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/mycash")
    public String mycash() {
        return "/login/landing_page";
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestParam("nome") String nome,
                                    @RequestParam("email") String email,
                                    @RequestParam("senha") String senha,
                                    @RequestParam("telefone") String telefone,
                                    @RequestParam("moedaPrincipal") String moedaPrincipal,
                                    RedirectAttributes redirectAttributes) {

        if (usuarioRepository.findByEmail(email).isPresent()) {
            redirectAttributes.addFlashAttribute("mensagem", "Email já cadastrado!");
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }

        if(usuarioRepository.findByTelefone(telefone).isPresent()) {
            redirectAttributes.addFlashAttribute("mensagem", "Telefone já cadastrado!");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setTelefone(telefone);
        usuario.setMoedaPreferida(moedaPrincipal);

        usuarioRepository.save(usuario);

        redirectAttributes.addFlashAttribute("mensagemSucesso", "Usuário criado com sucesso!");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/entrar")
    public String entrar() {
        return "/login/entrar";
    }
}
