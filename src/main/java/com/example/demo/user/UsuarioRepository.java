package com.example.demo.user;

import com.example.demo.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);  // ← TEM QUE TER!
    boolean existsByEmail(String email);

    boolean existsByNomeusuario(String nomeusuario);
}