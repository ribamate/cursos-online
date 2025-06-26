package com.Acadia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaCursoController {

    @GetMapping("/cursos/lista")
    public String mostrarListaCursos() {
        return "listadecursos"; // arquivo HTML direcionado
    }

     @GetMapping("/assinaturas")
    public String exibirPaginaAssinaturas() {
        return "assinaturas"; 
    }

    @GetMapping("/pagamento")
    public String exibirPaginaPagamento() {
        return "pagamento"; // Página de pagamento fictício
    }
}
