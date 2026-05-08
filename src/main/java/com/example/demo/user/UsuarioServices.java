package com.example.demo.user;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //aqui mantem as regras de negocio
public class UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository; // chama om banco

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizarParcial(Long id, @NonNull Usuario dados) { //mini validador pra atualizar parte do dado recebido
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        if (dados.getNome() != null) {
            usuario.setNome(dados.getNome());
        }
        if (dados.getEmail() != null) {
            usuario.setEmail(dados.getEmail());
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarEmail(Long id, String senhaInformada, String novoEmail) { //msm coisa so q pra email
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );
        if (!usuario.getSenha().equals(senhaInformada)) {
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