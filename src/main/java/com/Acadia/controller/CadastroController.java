package com.Acadia.controller;

import com.Acadia.model.Usuario;
import com.Acadia.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class CadastroController {

    private final UsuarioService usuarioService;

    // Injeção de dependência via construtor
    public CadastroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "paginaDeCadastro"; // Nome do HTML (sem .html)
    }

    // Recebe o POST do formulário e salva o usuário
    @PostMapping("/cadastrar")

    public String cadastrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.salvar(usuario); // A linha que salva no banco!
        return "redirect:/login?cadastroSucesso"; // redireciona para login com mensagem
    }
}
