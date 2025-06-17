package com.Acadia.service;

import com.Acadia.model.Assinatura;
import com.Acadia.model.Usuario;
import com.Acadia.repository.AssinaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssinaturaService {

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    public Assinatura salvar(Assinatura assinatura) {
        return assinaturaRepository.save(assinatura);
    }

    public List<Assinatura> listarTodas() {
        return assinaturaRepository.findAll();
    }

    public List<Assinatura> listarPorAluno(Usuario aluno) {
        return assinaturaRepository.findByAluno(aluno);
    }
}
