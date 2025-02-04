package com.ogl.mycash.controller;

import com.ogl.mycash.model.Usuario;
import com.ogl.mycash.repository.UsuarioRepository;
import com.ogl.mycash.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

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

        usuarioService.salvar(usuario);

        redirectAttributes.addFlashAttribute("mensagemSucesso", "Usuário criado com sucesso!");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/entrar")
    public String entrar() {
        return "/login/entrar";
    }

    @PostMapping("/logar")
    public ResponseEntity logar(@RequestParam("email")String email,
                                @RequestParam("senha")String senha,
                                HttpServletRequest request,
                                HttpServletResponse response) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        try {
            if (usuarioOptional.isPresent()) {
                if (!passwordEncoder.matches(senha, usuarioOptional.get().getSenha())) { // senha incorreta
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }
                if (!(email.equals(usuarioOptional.get().getEmail()))) { // email incorreto
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }
            }
            Usuario usuario = usuarioOptional.get();

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(usuario.getEmail(), senha);

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);

            HttpSessionSecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
            securityContextRepository.saveContext(context, request, response);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Erro de autenticação: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
