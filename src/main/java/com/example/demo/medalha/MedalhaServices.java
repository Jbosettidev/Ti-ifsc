package com.example.demo.medalha;

import com.example.demo.user.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedalhaServices {

    @Autowired
    private MedalhaRepository medalhaRepository;

    public List<Medalha> listarTodas() {
        return medalhaRepository.findAll();
    }

    public Medalha buscar(Long id) {
        return medalhaRepository.findById(id).orElseThrow();
    }

    public Medalha buscarPorNome(Medalha nome) { //quase inutil mas vou deixar pra facilitar agora
        return medalhaRepository.findByNome(nome.getNome()).orElseThrow();
    }

    /*  // ! aqui mudar pra atualizar o status do user com a medalha pra ele !
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
    } */
}
