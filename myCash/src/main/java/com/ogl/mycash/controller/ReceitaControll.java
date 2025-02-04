package com.ogl.mycash.controller;

import com.ogl.mycash.model.Receita;
import com.ogl.mycash.service.ReceitaService;
import com.ogl.mycash.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/receita")
public class ReceitaControll {

    @Autowired
    private ReceitaService receitaService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/salvarreceita")
    public String salvarReceita(@RequestParam("descricao")String descricao,
                                @RequestParam("valor")String valor,
                                @RequestParam("dataRecebimento")String dataRecebimento,
                                @RequestParam("categoria")String categoria,
                                RedirectAttributes redirectAttributes) {
        Receita receita = new Receita();
        receita.setDescricao(descricao);
        receita.setValor(Double.valueOf(valor));
        receita.setDataRecebimento(LocalDate.parse(dataRecebimento));
        receita.setCategoria(categoria);
        receita.setUsuario(usuarioService.getUsuarioLogado());
        receitaService.salvar(receita);

        redirectAttributes.addFlashAttribute("primeiraReceitaAdicionada", true);
        return "redirect:/home";
    }

    @GetMapping("/minhas-receitas")
    public String minhasReceitas(Model model) {
        model.addAttribute("receitasUsuario", receitaService.findByUsuarioId(usuarioService.getUsuarioLogado().getId()));
        return "/receita/minhas_receitas";
    }
}
