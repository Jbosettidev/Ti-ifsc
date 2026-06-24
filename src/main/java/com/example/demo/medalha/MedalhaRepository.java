package com.example.demo.medalha;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedalhaRepository extends JpaRepository<Medalha, Long> {
    Optional<Medalha> findByNome(String nome);
    Optional<User> findByUsuarioIdAndMedalhaId(Long usuarioId, Long medalhaId); //conferir
}
