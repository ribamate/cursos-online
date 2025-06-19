package com.Acadia.repository;

import com.Acadia.model.Curso;
import com.Acadia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Busca todos os cursos de um professor específico
    List<Curso> findByProfessor(Usuario professor);

    // Busca cursos por categoria (exemplo extra)
    List<Curso> findByCategoriaContainingIgnoreCase(String categoria);
}
