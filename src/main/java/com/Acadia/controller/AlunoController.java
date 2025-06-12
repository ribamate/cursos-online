package com.Acadia.controller;

import com.Acadia.model.Usuario;
import com.Acadia.repository.UsuarioRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlunoController {

    private final UsuarioRepository usuarioRepository;

    public AlunoController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/aluno")
    public String paginaAluno(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // Recupera o e-mail do usuário logado
        String email = userDetails.getUsername();

        // Busca o usuário no banco de dados
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + email));

        // Adiciona o nome do usuário ao modelo
        model.addAttribute("nome", usuario.getNome());

        return "paginadoaluno";
    }
}
