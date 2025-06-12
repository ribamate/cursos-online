package com.Acadia.controller;

import com.Acadia.model.Usuario;
import com.Acadia.service.UsuarioService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }
}
