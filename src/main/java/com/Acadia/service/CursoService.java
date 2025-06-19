package com.Acadia.service;

import com.Acadia.model.Curso;
import com.Acadia.model.Usuario;
import com.Acadia.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public List<Curso> listarPorProfessor(Usuario professor) {
        return cursoRepository.findByProfessor(professor);
    }

    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public void excluir(Long id) {
        cursoRepository.deleteById(id);
    }
}
