package com.ogl.mycash.controller;

import com.ogl.mycash.service.ReceitaService;
import com.ogl.mycash.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/home")
    public String home() {
        return "/home/home_primeiro_login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("usuario", usuarioService.getUsuarioLogado());
        model.addAttribute("receitaUsuario", receitaService.getTotalReceitasByUsuarioId(usuarioService.getUsuarioLogado().getId()));
        return "/home/home";
    }

    @GetMapping("/adicionar-receita")
    public String adicionarReceita(Model model) {
        model.addAttribute("moedaPrincipal", usuarioService.getMoedaPrincipalByUsuario(usuarioService.getUsuarioLogado()));
        return "/receita/adicionar_receita";
    }

    @GetMapping("/adicionar-categorias")
    public String personalizarCategorias(Model model) {
        return "/categoria/adicionar_categorias";
    }
}
