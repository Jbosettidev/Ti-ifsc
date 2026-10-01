package com.example.demo.fase;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Acesso a dados de {@link Fase}; métodos extras seguem convenção de nome do Spring Data JPA.
 */
public interface FaseRepository extends JpaRepository<Fase, Long> {
    Optional<Fase> findByTitulo(String titulo);
}