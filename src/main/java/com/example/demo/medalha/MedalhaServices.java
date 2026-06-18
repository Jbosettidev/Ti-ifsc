package com.example.demo.medalha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedalhaServices {

    @Autowired
    private MedalhaRepository medalhaRepository;

    public List<Medalha> listarTodas() {
        return medalhaRepository.findAll();
    }

    public Medalha buscar(Long id) {
        return medalhaRepository.findById(id).orElseThrow();
    }
}
