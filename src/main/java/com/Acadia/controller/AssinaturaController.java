package com.Acadia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AssinaturaController {

    @GetMapping("/assinaturas")
    public String exibirPaginaAssinaturas() {
        return "assinaturas"; // retorna o arquivo assinaturas.html (sem a extensão)
    }
}
