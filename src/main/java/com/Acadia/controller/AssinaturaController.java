package com.Acadia.controller;

import com.Acadia.model.Assinatura;
import com.Acadia.model.Usuario;
import com.Acadia.service.AssinaturaService;
import com.Acadia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/assinaturas")
public class AssinaturaController {

    @Autowired
    private AssinaturaService assinaturaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/nova")
    public String exibirPaginaAssinatura(Model model) {
        model.addAttribute("assinatura", new Assinatura());
        return "paginaAssinatura";
    }

    @PostMapping("/salvar")
    public String salvarAssinatura(@ModelAttribute Assinatura assinatura, Principal principal) {
        String emailLogado = principal.getName();
        Usuario aluno = usuarioService.buscarPorEmail(emailLogado);

        assinatura.setAluno(aluno);
        assinatura.setDataInicio(LocalDate.now());

        if ("ANUAL".equalsIgnoreCase(assinatura.getTipo())) {
            assinatura.setDataFim(LocalDate.now().plusYears(1));
        } else {
            assinatura.setDataFim(LocalDate.now().plusMonths(1));
        }

        assinatura.setStatus("ATIVA");
        assinaturaService.salvar(assinatura);
        return "redirect:/painel-aluno";
    }

    @GetMapping("/painel-aluno")
    public String painelAluno() {
        return "painelAluno";
    }
}
