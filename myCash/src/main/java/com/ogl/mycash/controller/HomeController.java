package com.ogl.mycash.controller;

import com.ogl.mycash.model.Receita;
import com.ogl.mycash.service.CategoriaService;
import com.ogl.mycash.service.DespesaService;
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
    @Autowired
    private DespesaService despesaService;

    @GetMapping("/home")
    public String home() {
        return "/home/home_primeiro_login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Float receitaUsuario = receitaService.getTotalReceitasByUsuarioId(usuarioService.getUsuarioLogado().getId());
        Float despesaUsuario = despesaService.getTotalDespesasByUsuarioId(usuarioService.getUsuarioLogado().getId());
        Float saldoUsuario = receitaUsuario - despesaUsuario;
        model.addAttribute("usuario", usuarioService.getUsuarioLogado());
        model.addAttribute("moedaUsuario", usuarioService.getMoedaPrincipalByUsuario(usuarioService.getUsuarioLogado()));
        model.addAttribute("receitaUsuario", receitaUsuario);
        model.addAttribute("despesaUsuario", despesaUsuario);
        model.addAttribute("saldoUsuario", saldoUsuario);
        return "/home/home";
    }

    @GetMapping("/adicionar-receita")
    public String adicionarReceita(Model model) {
        model.addAttribute("moedaPrincipal", usuarioService.getMoedaPrincipalByUsuario(usuarioService.getUsuarioLogado()));
        model.addAttribute("categoriaPorUsuario", categoriaService.findByUsuarioId(Long.valueOf(usuarioService.getUsuarioLogado().getId())));
        return "/receita/adicionar_receita";
    }

    @GetMapping("/adicionar-categorias")
    public String personalizarCategorias(Model model) {
        return "/categoria/adicionar_categorias";
    }
}
