package com.example.demo.controller;

import com.example.demo.dto.ProgressRequestDTO;
import com.example.demo.excessoes.ResourceNotFoundException;
import com.example.demo.medalha.MedalhaServices;
import com.example.demo.model.Lesson;
import com.example.demo.model.Level;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.LevelRepository;
import com.example.demo.user.UserLessonProgress;
import com.example.demo.user.UserLessonProgressRepository;
import com.example.demo.user.Usuario;
import com.example.demo.user.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final UserLessonProgressRepository progressoRepository;
    private final LessonRepository lessonRepository;
    private final LevelRepository levelRepository;
    private final UsuarioRepository usuarioRepository;
    private final MedalhaServices medalhaServices;

    /** Resumo do desempenho do usuário; a tela de conquistas usa para mostrar o % de acertos. */
    public record ResumoProgressoResponse(int licoesConcluidas, int totalAcertos, int totalPerguntas) {}

    @GetMapping
    public ResponseEntity<ResumoProgressoResponse> resumirProgresso(@RequestParam(defaultValue = "1") Long usuarioId) {
        List<UserLessonProgress> concluidas = progressoRepository.findByUsuario_Id(usuarioId);
        int acertos = concluidas.stream()
                .mapToInt(p -> p.getTotalAcertos() == null ? 0 : p.getTotalAcertos()).sum();
        int perguntas = concluidas.stream()
                .mapToInt(p -> p.getTotalPerguntas() == null ? 0 : p.getTotalPerguntas()).sum();
        return ResponseEntity.ok(new ResumoProgressoResponse(concluidas.size(), acertos, perguntas));
    }

    // usuarioId com padrão 1 só até o front mandar o usuário logado.
    @PostMapping
    public ResponseEntity<Void> salvarProgresso(@RequestBody ProgressRequestDTO dto,
                                                @RequestParam(defaultValue = "1") Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        Lesson licao = lessonRepository.findById(dto.getLessonId())
                .orElseThrow(() -> new ResourceNotFoundException("Lição não encontrada"));

        // Lição já concluída: é só revisão, não grava de novo nem soma XP.
        if (progressoRepository.existsByUsuario_IdAndLessonId(usuarioId, licao.getId())) {
            return ResponseEntity.ok().build();
        }

        UserLessonProgress progresso = new UserLessonProgress();
        progresso.setUsuario(usuario);
        progresso.setLessonId(licao.getId());
        progresso.setXpGanho(dto.getXpGanho());
        progresso.setTotalAcertos(dto.getTotalAcertos());
        progresso.setTotalPerguntas(dto.getTotalPerguntas());
        progresso.setConcluidoEm(LocalDateTime.now());
        progressoRepository.save(progresso);

        // Medalhas
        medalhaServices.avaliarPrimeiraMissao(usuarioId);
        medalhaServices.avaliarOfensiva(usuarioId);
        if (licao.isDesafioFinal() && dto.getTotalAcertos() != null && dto.getTotalPerguntas() != null) {
            medalhaServices.avaliarMedalhaDeJogoFinal(usuarioId, dto.getTotalAcertos(), dto.getTotalPerguntas());
        }
        avaliarSeNivelFoiCompletado(usuarioId, licao);

        return ResponseEntity.ok().build();
    }

    private void avaliarSeNivelFoiCompletado(Long usuarioId, Lesson licaoConcluida) {
        Level nivel = licaoConcluida.getLevel();
        if (nivel == null) return;

        boolean nivelCompleto = nivel.getLicoes().stream()
                .allMatch(l -> progressoRepository.existsByUsuario_IdAndLessonId(usuarioId, l.getId()));
        if (!nivelCompleto) return;

        List<UserLessonProgress> progressosDoNivel = nivel.getLicoes().stream()
                .map(l -> progressoRepository.findTopByUsuario_IdAndLessonIdOrderByConcluidoEmDesc(usuarioId, l.getId()))
                .collect(Collectors.toList());
        medalhaServices.avaliarMedalhasDeNivel(usuarioId, progressosDoNivel);

        Level ultimoNivel = levelRepository.findTopByOrderByOrdemDesc();
        if (nivel.getOrdem().equals(ultimoNivel.getOrdem())) {
            medalhaServices.avaliarMedalhaDeCurso(usuarioId);
        }
    }
}