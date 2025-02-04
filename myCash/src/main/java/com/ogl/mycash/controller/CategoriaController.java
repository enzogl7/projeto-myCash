package com.ogl.mycash.controller;

import com.ogl.mycash.model.Categoria;
import com.ogl.mycash.service.CategoriaService;
import com.ogl.mycash.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar")
    public String criarCategoria(@RequestParam("nomeCategoria") List<String> nomesCategorias,
                                 RedirectAttributes redirectAttributes) {
        for (String nome : nomesCategorias) {
            Categoria categoria = new Categoria();
            categoria.setNome(nome);
            categoria.setUsuario(usuarioService.getUsuarioLogado());
            categoriaService.salvar(categoria);
        }
        redirectAttributes.addFlashAttribute("primeiraReceitaAdicionada", true);
        redirectAttributes.addFlashAttribute("categoriaAdicionada", true);
        return "redirect:/home";

    }
}
