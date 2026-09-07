package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

// Regras de negócio e persistência.
@Service
public class UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Precisamos disso pra nunca mais comparar/guardar senha em texto puro.
    // O bean vem do SecurityConfig (BCryptPasswordEncoder).
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario salvar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        if (usuarioRepository.existsByNomeusuario(usuario.getNomeusuario())) {
            throw new RuntimeException("Nome de usuário já cadastrado");
        }

        // Antes: a senha ia pro banco exatamente como o usuário digitou.
        // Agora: guardamos só o HASH dela. Ninguém — nem quem tem acesso
        // direto ao banco — consegue ler a senha original a partir disso.
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email.trim())
                .orElseThrow(() -> new RuntimeException("E-mail ou senha incorretos"));

        // matches() faz o hash da senha digitada e compara com o hash
        // salvo — nunca descriptografa o que está no banco (hash não tem volta).
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("E-mail ou senha incorretos");
        }
        return usuario;
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Removido o "@NonNull" do parâmetro (vinha de org.jspecify, que não
    // está nas dependências do projeto — provável causa de erro de
    // compilação). Era só uma anotação de documentação, não muda o
    // comportamento: removê-la é seguro.
    public Usuario atualizarParcial(Long id, Usuario dados) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        if (dados.getNome() != null) {
            usuario.setNome(dados.getNome());
        }
        if (dados.getEmail() != null) {
            usuario.setEmail(dados.getEmail());
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarEmail(Long id, String senhaInformada, String novoEmail) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!passwordEncoder.matches(senhaInformada, usuario.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }
        if (usuarioRepository.existsByEmail(novoEmail)) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        usuario.setEmail(novoEmail);
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}