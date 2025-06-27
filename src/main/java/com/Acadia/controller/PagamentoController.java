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
    private AssinaturaService assinaturaService;

    @Autowired
    private UsuarioService usuarioService;

    // Exibe a página de confirmação do pagamento
    @GetMapping
    public String mostrarTelaPagamento(@RequestParam String plano, Model model) {
        model.addAttribute("planoSelecionado", plano.toUpperCase());
        return "pagamento";
    }

    // Processa a confirmação do pagamento
    @PostMapping("/confirmar")
    public String confirmarPagamento(@RequestParam String plano, Principal principal) {
        String emailUsuario = principal.getName();
        Usuario usuario = usuarioService.buscarPorEmail(emailUsuario);

        Assinatura assinatura = new Assinatura();
        assinatura.setAluno(usuario);
        assinatura.setTipo(plano.toUpperCase());
        assinatura.setDataInicio(LocalDate.now());

        if (plano.equalsIgnoreCase("anual")) {
            assinatura.setDataFim(LocalDate.now().plusYears(1));
        } else {
            assinatura.setDataFim(LocalDate.now().plusMonths(1));
        }

        assinatura.setStatus("ATIVA");
        assinaturaService.salvar(assinatura);

        return "redirect:/painel-aluno";
    }

    // Botão de cancelamento (opcional, mas pode ser usado se tiver lógica extra)
    @PostMapping("/cancelar")
    public String cancelarPagamento() {
        return "redirect:/assinaturas";
    }
}