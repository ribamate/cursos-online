package com.Acadia.security;

import com.Acadia.model.Usuario;
import com.Acadia.repository.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final UsuarioRepository usuarioRepository;

    public CustomSuccessHandler(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);

        if (usuario != null) {
            switch (usuario.getPerfil()) {
                case PROFESSOR -> response.sendRedirect("/cursos/novo");
                case ALUNO -> response.sendRedirect("/painel-aluno");
                case ADMINISTRADOR -> response.sendRedirect("/admin");
                default -> response.sendRedirect("/login?erro");
            }
        } else {
            response.sendRedirect("/login?erro");
        }
    }
}
