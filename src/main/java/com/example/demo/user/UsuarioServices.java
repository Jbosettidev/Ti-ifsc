package com.example.demo.user;

import com.example.demo.excessoes.ResourceNotFoundException;
import com.example.demo.medalha.UsuarioMedalhaRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class UsuarioServices {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMedalhaRepository usuarioMedalhaRepository;
    private final UserLessonProgressRepository progressoRepository;

    public UsuarioServices(UsuarioRepository usuarioRepository,
                           PasswordEncoder passwordEncoder,
                           UsuarioMedalhaRepository usuarioMedalhaRepository,
                           UserLessonProgressRepository progressoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMedalhaRepository = usuarioMedalhaRepository;
        this.progressoRepository = progressoRepository;
    }

    public Usuario salvar(Usuario usuario) {
        String email = normalizarEmail(usuario.getEmail());
        usuario.setEmail(email);
        if (usuarioRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
        if (usuarioRepository.existsByNomeusuario(usuario.getNomeusuario())) {
            throw new IllegalArgumentException("Nome de usuário já cadastrado");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(normalizarEmail(email))
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    /** Busca o usuário e garante que ele é o próprio usuário logado. */
    public Usuario buscarDoUsuario(Long id, String emailAutenticado) {
        Usuario usuario = buscar(id);
        if (!usuario.getEmail().equalsIgnoreCase(emailAutenticado)) {
            throw new AccessDeniedException("Acesso não autorizado");
        }
        return usuario;
    }

    public Usuario atualizarParcial(Long id, Usuario dados, String emailAutenticado) {
        Usuario usuario = buscarDoUsuario(id, emailAutenticado);

        if (dados.getNome() != null && !dados.getNome().isBlank()) {
            usuario.setNome(dados.getNome().trim());
        }
        if (dados.getEmail() != null && !dados.getEmail().isBlank()) {
            String novoEmail = normalizarEmail(dados.getEmail());
            if (!novoEmail.equalsIgnoreCase(usuario.getEmail()) && usuarioRepository.existsByEmail(novoEmail)) {
                throw new IllegalArgumentException("E-mail já cadastrado");
            }
            usuario.setEmail(novoEmail);
        }
        // Senha não é alterada por aqui (veja atualizarSenha).
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarSenha(Long id, String senhaAtual, String novaSenha, String emailAutenticado) {
        Usuario usuario = buscarDoUsuario(id, emailAutenticado);

        if (senhaAtual == null || !passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new IllegalArgumentException("Senha incorreta");
        }
        if (novaSenha == null || novaSenha.length() < 8) {
            throw new IllegalArgumentException("A nova senha deve possuir pelo menos 8 caracteres");
        }
        usuario.setSenha(passwordEncoder.encode(novaSenha));
        return usuarioRepository.save(usuario);
    }

    /** Apaga também medalhas e progresso do usuário, senão a chave estrangeira impede o delete. */
    @Transactional
    public void deletar(Long id, String emailAutenticado) {
        buscarDoUsuario(id, emailAutenticado);
        usuarioMedalhaRepository.deleteByUsuario_Id(id);
        progressoRepository.deleteByUsuario_Id(id);
        usuarioRepository.deleteById(id);
    }

    private String normalizarEmail(String email) {
        return email == null ? null : email.trim().toLowerCase(Locale.ROOT);
    }
}
