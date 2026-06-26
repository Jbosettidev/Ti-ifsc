package com.example.demo.medalha;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedalhaRepository extends JpaRepository<Medalha, Long> {
    Optional<Medalha> findByNome(String nome);
}
