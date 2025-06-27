package com.Acadia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaInicialController {

    @GetMapping({ "/", "/paginainicial" })
    public String exibirPaginaInicial() {
        return "paginainicial"; // Vai procurar em templates/paginainicial.html
    }
}