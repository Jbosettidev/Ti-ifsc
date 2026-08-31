package com.example.demo.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;

@Service
public class UsuarioServices {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServices(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario salvar(Usuario usuario) {
        String email = normalizarEmail(usuario.getEmail());
        usuario.setEmail(email);
        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        if (usuarioRepository.existsByNomeusuario(usuario.getNomeusuario())) {
            throw new RuntimeException("Nome de usuário já cadastrado");
        }
        // Criptografa a senha antes de salvar
        usuario.setSenha(
                passwordEncoder.encode(usuario.getSenha())
        );
        return usuarioRepository.save(usuario);
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado")
                );
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(normalizarEmail(email)).orElseThrow(() ->
                new RuntimeException("Usuário não encontrado")
        );
    }

    public Usuario buscarDoUsuario(Long id, String emailAutenticado) {
        Usuario usuario = buscar(id);
        if (!usuario.getEmail()
                .equalsIgnoreCase(emailAutenticado)) {
            throw new org.springframework.security.access.AccessDeniedException("Acesso não autorizado");
        }
        return usuario;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizarParcial(Long id, Usuario dados, String emailAutenticado) {
        Usuario usuario =
                buscarDoUsuario(id, emailAutenticado);
        if (dados.getNome() != null && !dados.getNome().isBlank()) {
            usuario.setNome(dados.getNome().trim());
        }if (dados.getEmail() != null && !dados.getEmail().isBlank()) {
            String novoEmail = normalizarEmail(dados.getEmail());
            if (!novoEmail.equalsIgnoreCase(usuario.getEmail()) && usuarioRepository.existsByEmail(novoEmail)) {
                throw new RuntimeException("E-mail já cadastrado");
            }
            usuario.setEmail(novoEmail);
        }
        // Senha não pode ser alterada por aqui.
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarSenha(Long id, String senhaAtual, String novaSenha, String emailAutenticado) {
        Usuario usuario =
                buscarDoUsuario(id, emailAutenticado);
        // Compara a senha digitada com o hash
        if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }if (novaSenha == null || novaSenha.isBlank() || novaSenha.length() < 8) {
            throw new RuntimeException("A nova senha deve possuir pelo menos 8 caracteres");
        }
        // Gera um novo hash
        usuario.setSenha(passwordEncoder.encode(novaSenha));
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id, String emailAutenticado) {
        buscarDoUsuario(id, emailAutenticado);
        usuarioRepository.deleteById(id);
    }

    private String normalizarEmail(String email) {
        if (email == null) {
            return null;
        }
        return email.trim().toLowerCase(Locale.ROOT);
    }
}