package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositório Spring Data JPA para {@link Usuario}.
 * <p>
 * {@link JpaRepository} já fornece {@code save}, {@code findById}, {@code findAll},
 * {@code deleteById}, etc. Métodos declarados aqui ganham implementação gerada por convenção
 * de nome ({@code findByEmail}, {@code existsByEmail}).
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String novoEmail);

    boolean existsByNomeusuario(String nomeusuario);
}