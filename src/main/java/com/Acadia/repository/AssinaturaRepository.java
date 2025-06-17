package com.Acadia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.Acadia.model.Usuario;
import com.Acadia.model.Assinatura;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    List<Assinatura> findByAluno(Usuario aluno);
}
