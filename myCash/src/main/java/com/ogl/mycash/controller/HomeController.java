package com.ogl.mycash.controller;

import com.ogl.mycash.model.Receita;
import com.ogl.mycash.service.CategoriaService;
import com.ogl.mycash.service.ReceitaService;
import com.ogl.mycash.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ReceitaService receitaService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/home")
    public String home() {
        return "/home/home_primeiro_login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("usuario", usuarioService.getUsuarioLogado());
        model.addAttribute("receitaUsuario", receitaService.getTotalReceitasByUsuarioId(usuarioService.getUsuarioLogado().getId()));
        model.addAttribute("moedaUsuario", usuarioService.getMoedaPrincipalByUsuario(usuarioService.getUsuarioLogado()));
        return "/home/home";
    }

    @GetMapping("/adicionar-receita")
    public String adicionarReceita(Model model) {
        model.addAttribute("moedaPrincipal", usuarioService.getMoedaPrincipalByUsuario(usuarioService.getUsuarioLogado()));
        model.addAttribute("categoriaPorUsuario", categoriaService.findByUsuarioId(Long.valueOf(usuarioService.getUsuarioLogado().getId())));
        List<Receita> receitasUsuario = receitaService.findByUsuarioId(usuarioService.getUsuarioLogado().getId());
        model.addAttribute("temReceitas", !receitasUsuario.isEmpty());
        return "/receita/adicionar_receita";
    }

    @GetMapping("/adicionar-categorias")
    public String personalizarCategorias(Model model) {
        return "/categoria/adicionar_categorias";
    }
}
