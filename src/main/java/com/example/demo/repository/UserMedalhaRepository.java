package com.example.demo.repository;

import com.example.demo.model.Medalha;
import com.example.demo.model.UserMedalha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMedalhaRepository extends JpaRepository<UserMedalha, Long> {
    boolean existsByUsuario_IdAndMedalha(Long usuarioId, Medalha medalha);
}