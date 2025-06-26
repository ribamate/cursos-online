package com.Acadia.controller;

import com.Acadia.model.Assinatura;
import com.Acadia.model.Usuario;
import com.Acadia.service.AssinaturaService;
import com.Acadia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AssinaturaService assinaturaService;

    @GetMapping
    public String exibirFormularioPagamento(@RequestParam String plano, Model model) {
        model.addAttribute("plano", plano);
        return "pagamento";
    }

    @PostMapping("/confirmar")
    public String confirmarPagamento(@RequestParam String plano, Principal principal) {
        Usuario aluno = usuarioService.buscarPorEmail(principal.getName());

        Assinatura assinatura = new Assinatura();
        assinatura.setAluno(aluno);
        assinatura.setTipo(plano);
        assinatura.setDataInicio(LocalDate.now());
        assinatura.setStatus("ATIVA");

        if ("ANUAL".equalsIgnoreCase(plano)) {
            assinatura.setDataFim(LocalDate.now().plusYears(1));
        } else {
            assinatura.setDataFim(LocalDate.now().plusMonths(1));
        }

        assinaturaService.salvar(assinatura);

        return "redirect:/aluno";
    }
}
