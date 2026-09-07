package com.example.demo.repository;

import com.example.demo.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Long> {
    // Acha o nível com o maior "ordem" — usamos pra saber se o usuário
    // terminou o ÚLTIMO nível (ou seja, o curso inteiro).
    Level findTopByOrderByOrdemDesc();
}