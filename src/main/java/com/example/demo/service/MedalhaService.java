package com.example.demo.service;

import com.example.demo.model.Medalha;
import com.example.demo.model.UserLessonProgress;
import com.example.demo.model.UserMedalha;
import com.example.demo.repository.UserMedalhaRepository;
import com.example.demo.user.Usuario;
import com.example.demo.user.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedalhaService {

    @Autowired
    private UserMedalhaRepository userMedalhaRepository;

    // Precisamos disso agora: os métodos públicos ainda recebem só o
    // "usuarioId" (é mais prático pra quem chama), mas pra SALVAR a
    // UserMedalha, o campo lá dentro é um Usuario de verdade (não um
    // Long solto) — então buscamos o Usuario aqui dentro antes de salvar.
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void avaliarMedalhasDeNivel(Long usuarioId, List<UserLessonProgress> progressosDoNivel) {
        double mediaAproveitamento = progressosDoNivel.stream()
                .mapToDouble(UserLessonProgress::calcularAproveitamento)
                .average()
                .orElse(0.0);

        if (mediaAproveitamento >= 100.0) {
            concederMedalha(usuarioId, Medalha.TERMINAR_NIVEL_100);
        } else if (mediaAproveitamento >= 80.0) {
            concederMedalha(usuarioId, Medalha.TERMINAR_NIVEL_80);
        }
    }

    public void avaliarMedalhaDeCurso(Long usuarioId) {
        concederMedalha(usuarioId, Medalha.FINALIZAR_CURSO);
    }

    public void avaliarMedalhaDeOfensiva(Long usuarioId, int diasSeguidos) {
        if (diasSeguidos >= 30) concederMedalha(usuarioId, Medalha.OFENSIVA_30_DIAS);
        else if (diasSeguidos >= 15) concederMedalha(usuarioId, Medalha.OFENSIVA_15_DIAS);
        else if (diasSeguidos >= 7) concederMedalha(usuarioId, Medalha.OFENSIVA_7_DIAS);
    }

    public void avaliarMedalhaDeJogoFinal(Long usuarioId, int acertos, int totalPerguntas) {
        if (acertos == totalPerguntas) {
            concederMedalha(usuarioId, Medalha.JOGO_FINAL_PONTUACAO_MAXIMA);
        }
    }

    private void concederMedalha(Long usuarioId, Medalha medalha) {
        boolean jaTem = userMedalhaRepository.existsByUsuario_IdAndMedalha(usuarioId, medalha);
        if (jaTem) return;

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UserMedalha conquista = new UserMedalha();
        conquista.setUsuario(usuario);
        conquista.setMedalha(medalha);
        conquista.setConquistadaEm(LocalDateTime.now());
        userMedalhaRepository.save(conquista);
    }
}