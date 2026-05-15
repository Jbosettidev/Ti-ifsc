package com.example.demo.user;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Camada de serviço do domínio {@link Usuario}: orquestra regras de negócio e persistência
 * via {@link UsuarioRepository}. Controllers devem delegar aqui em vez de acessar o
 * repositório diretamente, para manter validações e regras em um só lugar.
 */
@Service
public class UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Atualiza apenas campos não nulos do payload (PATCH semântico): nome e/ou e-mail.
     */
    public Usuario atualizarParcial(Long id, @NonNull Usuario dados) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        if (dados.getNome() != null) {
            usuario.setNome(dados.getNome());
        }
        if (dados.getEmail() != null) {
            usuario.setEmail(dados.getEmail());
        }

        return usuarioRepository.save(usuario);
    }

    /**
     * Troca o e-mail se a senha informada confere e o novo e-mail ainda não existe.
     *
     * @throws RuntimeException usuário inexistente, senha incorreta ou e-mail já cadastrado
     */
    public Usuario atualizarEmail(Long id, String senhaInformada, String novoEmail) {
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