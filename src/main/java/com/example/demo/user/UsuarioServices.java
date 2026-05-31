package com.example.demo.user;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Camada de serviço do domínio {@link Usuario}: orquestra regras de negócio e persistência*/
@Service
public class UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }if (usuarioRepository.existsByNomeusuario(usuario.getNomeusuario())) {
            throw new RuntimeException("Nome de usuário já cadastrado");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email.trim())
                .orElseThrow(() -> new RuntimeException("E-mail ou senha incorretos"));
        if (!usuario.getSenha().equals(senha)) {
            throw new RuntimeException("E-mail ou senha incorretos");
        }return usuario;
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizarParcial(Long id, @NonNull Usuario dados) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        if (dados.getNome() != null) {
            usuario.setNome(dados.getNome());
        }if (dados.getEmail() != null) {
            usuario.setEmail(dados.getEmail());
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarEmail(Long id, String senhaInformada, String novoEmail) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );if (!usuario.getSenha().equals(senhaInformada)) {
            throw new RuntimeException("Senha incorreta");
        }if (usuarioRepository.existsByEmail(novoEmail)) {
            throw new RuntimeException("E-mail já cadastrado");
        }
        usuario.setEmail(novoEmail);
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        } else{
            usuarioRepository.deleteById(id);}
    }
}