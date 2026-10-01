package com.example.demo.medalha;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioMedalhaRepository extends JpaRepository<UsuarioMedalha, Long> {

    Optional<UsuarioMedalha> findByUsuario_IdAndMedalha_Id(Long usuarioId, Long medalhaId);

    List<UsuarioMedalha> findByUsuario_Id(Long usuarioId);

    void deleteByUsuario_Id(Long usuarioId);
}
