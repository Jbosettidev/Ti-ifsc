package com.example.demo.service;

import com.example.demo.model.Medalha;
import com.example.demo.model.UserLessonProgress;
import org.springframework.stereotype.Service;

import java.util.List;

// Esse serviço concentra TODAS as regras de "quando dar uma medalha".
// Ele é chamado depois que uma lição (ou nível, ou o curso todo) é
// concluído — nunca do frontend, porque as regras dependem de histórico
// que só o backend tem acesso (todas as tentativas do usuário).
@Service
public class MedalhaService {

    // Chamado assim que o usuário termina TODAS as lições de um nível.
    // "progressosDoNivel" é a lista de UserLessonProgress daquele nível.
    public void avaliarMedalhasDeNivel(Long userId, List<UserLessonProgress> progressosDoNivel) {

        double mediaAproveitamento = progressosDoNivel.stream()
                .mapToDouble(UserLessonProgress::calcularAproveitamento)
                .average()
                .orElse(0.0);

        // Diamante: 100% de aproveitamento no nível inteiro
        if (mediaAproveitamento >= 100.0) {
            concederMedalha(userId, Medalha.TERMINAR_NIVEL_100);
        }
        // Prata: entre 80% e 99%
        else if (mediaAproveitamento >= 80.0) {
            concederMedalha(userId, Medalha.TERMINAR_NIVEL_80);
        }
        // Abaixo de 80%: nenhuma medalha de nível dessa vez — e tudo bem,
        // o usuário já avançou de nível mesmo assim, só não ganhou o troféu.
    }

    // Chamado quando o usuário termina a última lição do curso (nível 4).
    public void avaliarMedalhaDeCurso(Long userId) {
        concederMedalha(userId, Medalha.FINALIZAR_CURSO);
    }

    // Chamado todo dia, ao logar, comparando com a última data de acesso salva.
    public void avaliarMedalhaDeOfensiva(Long userId, int diasSeguidos) {
        if (diasSeguidos >= 30) concederMedalha(userId, Medalha.OFENSIVA_30_DIAS);
        else if (diasSeguidos >= 15) concederMedalha(userId, Medalha.OFENSIVA_15_DIAS);
        else if (diasSeguidos >= 7) concederMedalha(userId, Medalha.OFENSIVA_7_DIAS);
    }

    // Chamado depois do "desafio final" (o jogo/quiz maior de cada nível).
    public void avaliarMedalhaDeJogoFinal(Long userId, int acertos, int totalPerguntas) {
        if (acertos == totalPerguntas) {
            concederMedalha(userId, Medalha.JOGO_FINAL_PONTUACAO_MAXIMA);
        }
    }

    // Aqui você salvaria numa tabela "user_medalha" (userId + medalha + data).
    // Antes de salvar, sempre verifique se o usuário JÁ tem essa medalha,
    // pra não duplicar.
    private void concederMedalha(Long userId, Medalha medalha) {
        // TODO: verificar se já existe, e se não existir, salvar no banco
        // e talvez notificar o frontend (ex: mostrar um pop-up de conquista).
        System.out.println("Usuário " + userId + " ganhou a medalha: " + medalha);
    }
}