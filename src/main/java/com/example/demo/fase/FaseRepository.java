package com.example.demo.fase;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FaseRepository extends JpaRepository<Fase, Long> {
    Optional<Fase> findByTitulo(String titulo);
}