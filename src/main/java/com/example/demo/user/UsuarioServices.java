package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public Usuario salvar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        if (usuarioRepository.existsByNomeusuario(usuario.getNomeusuario())) {
            throw new RuntimeException("Nome de usuário já cadastrado");
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email.trim())
                .orElseThrow(() -> new RuntimeException("E-mail ou senha incorretos"));
        if (!encoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("E-mail ou senha incorretos");
        }
        return usuario;
    }

    public boolean verificarSenha(String senhaDigitada, String senhaHash) {
        return encoder.matches(senhaDigitada, senhaHash);
    }

    // BUSCAR POR ID
    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizarParcial(Long id, @NonNull Usuario dados) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );
        if (dados.getNome() != null) {
            usuario.setNome(dados.getNome());
        }
        if (dados.getEmail() != null) {
            if (!dados.getEmail().equals(usuario.getEmail()) &&
                    usuarioRepository.existsByEmail(dados.getEmail())) {
                throw new RuntimeException("E-mail já cadastrado");
            }
            usuario.setEmail(dados.getEmail());
        }
        if (dados.getSenha() != null && !dados.getSenha().isEmpty()) {
            usuario.setSenha(encoder.encode(dados.getSenha()));
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarEmail(Long id, String senhaInformada, String novoEmail) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );
        if (!encoder.matches(senhaInformada, usuario.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }
        if (usuarioRepository.existsByEmail(novoEmail)) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        usuario.setEmail(novoEmail);
        return usuarioRepository.save(usuario);
    }

    // DELETAR
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}