package com.ogl.mycash.controller;

import com.ogl.mycash.model.Despesa;
import com.ogl.mycash.service.DespesaService;
import com.ogl.mycash.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/despesa")
public class DespesaController {
    @Autowired
    private DespesaService despesaService;
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/minhas-despesas")
    public String minhasDespesas(Model model) {
        model.addAttribute("despesasUsuario", despesaService.findByUsuarioId(usuarioService.getUsuarioLogado().getId()));
        model.addAttribute("moedaUsuario", usuarioService.getMoedaPrincipalByUsuario(usuarioService.getUsuarioLogado()));
        return "/despesa/minhas-despesas";
    }

    @GetMapping("/adicionar-despesa")
    public String adicionarDespesa(Model model) {
        model.addAttribute("moedaPrincipal", usuarioService.getMoedaPrincipalByUsuario(usuarioService.getUsuarioLogado()));
        return "/despesa/adicionar-despesa";
    }

    @PostMapping("/salvardespesa")
    public String salvarDespesa(@RequestParam("descricaoDespesa") String descricao,
                                @RequestParam("valorDespesa") String valor,
                                @RequestParam("dataDespesa") String data,
                                @RequestParam("iconeDespesa") MultipartFile logo) {

        try {
            Despesa despesa = new Despesa();
            despesa.setDescricao(descricao);
            despesa.setValor(Double.valueOf(valor));
            despesa.setData(LocalDate.parse(data));
            despesa.setUsuario(usuarioService.getUsuarioLogado());

            if (!logo.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + logo.getOriginalFilename();
                Path uploadPath = Paths.get("uploads/");
                Files.createDirectories(uploadPath);
                Files.copy(logo.getInputStream(), uploadPath.resolve(fileName));
                despesa.setLogoPath(fileName);
            }

            despesa.setUsuario(usuarioService.getUsuarioById(usuarioService.getUsuarioLogado().getId()));
            despesaService.salvar(despesa);
            return "redirect:/despesa/minhas-despesas";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

    }
}
