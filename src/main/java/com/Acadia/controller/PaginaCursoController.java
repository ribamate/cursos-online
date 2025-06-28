package com.Acadia.controller;

@Controller
@RequestMapping("/cursos")
public class PaginaCursoController {

    @GetMapping("/lista")
    public String mostrarListaCursos() {
        return "listadecursos"; // nome do HTML sem .html
    }
}
