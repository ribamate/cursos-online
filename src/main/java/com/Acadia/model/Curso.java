package com.Acadia.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private String categoria;

    @ManyToOne
    private Usuario professor;

    @ManyToMany
    @JoinTable(name = "curso_aluno", joinColumns = @JoinColumn(name = "curso_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private Set<Usuario> alunos = new HashSet<>();
}
