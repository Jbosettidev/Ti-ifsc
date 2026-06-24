package com.example.demo.medalha;

import com.example.demo.fase.Fase;
import com.example.demo.user.Usuario;
import com.example.demo.user.UsuarioRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedalhaServices {

    @Autowired
    private MedalhaRepository medalhaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Medalha> listarTodas() {
        return medalhaRepository.findAll();
    }

    public Medalha buscar(Long id) {
        return medalhaRepository.findById(id).orElseThrow();
    }

    public Medalha buscarPorNome(Medalha nome) { //quase inutil mas vou deixar pra facilitar agora
        return medalhaRepository.findByNome(nome.getNome()).orElseThrow();
    }

    public Medalha marcarComoConcluido(Long id) {
        Medalha medalha = medalhaRepository.findById(id).orElseThrow(() -> new RuntimeException("Medalha não encontrada"));
        medalha.setObjConculuido(true);
        return medalhaRepository.save(medalha);
    }

    /*
    public User marcarMedalhaComoConcluida(Long usuarioId, Long medalhaId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Medalha medalha = medalhaRepository.findById(medalhaId)
                .orElseThrow(() -> new RuntimeException("Medalha não encontrada"));

        User usuarioMedalha = userRepository
                .findByUsuarioIdAndMedalhaId(usuarioId, medalhaId)
                .orElse(new UsuarioMedalha());

        usuarioMedalha.setUsuario(usuario);
        usuarioMedalha.setMedalha(medalha);
        usuarioMedalha.setConcluido(true);

        return medalhaRepository.save(medalha);
    }*/
}
