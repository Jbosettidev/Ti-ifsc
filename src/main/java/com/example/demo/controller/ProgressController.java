package com.example.demo.controller;

import com.example.demo.dto.ProgressRequestDTO;
import com.example.demo.model.Lesson;
import com.example.demo.model.Level;
import com.example.demo.model.UserLessonProgress;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.LevelRepository;
import com.example.demo.repository.UserLessonProgressRepository;
import com.example.demo.service.MedalhaService;
import com.example.demo.user.Usuario;
import com.example.demo.user.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired private UserLessonProgressRepository progressoRepository;
    @Autowired private LessonRepository lessonRepository;
    @Autowired private LevelRepository levelRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private MedalhaService medalhaService;

    // O parâmetro mudou de "userId" pra "usuarioId" (mesmo nome da
    // entidade agora). Continua com valor padrão "1" só até existir
    // login/sessão de verdade plugado aqui.
    @PostMapping
    public void salvarProgresso(@RequestBody ProgressRequestDTO dto,
                                 @RequestParam(defaultValue = "1") Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UserLessonProgress progresso = new UserLessonProgress();
        progresso.setUsuario(usuario);
        progresso.setLessonId(dto.getLessonId());
        progresso.setXpGanho(dto.getXpGanho());
        progresso.setTotalAcertos(dto.getTotalAcertos());
        progresso.setTotalPerguntas(dto.getTotalPerguntas());
        progresso.setConcluidoEm(LocalDateTime.now());
        progressoRepository.save(progresso);

        avaliarSeNivelFoiCompletado(usuarioId, dto.getLessonId());
    }

    private void avaliarSeNivelFoiCompletado(Long usuarioId, Long lessonId) {
        Lesson licaoConcluida = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lição não encontrada"));
        Level nivel = licaoConcluida.getLevel();

        boolean nivelCompleto = nivel.getLicoes().stream()
                .allMatch(l -> progressoRepository.existsByUsuario_IdAndLessonId(usuarioId, l.getId()));

        if (!nivelCompleto) return;

        List<UserLessonProgress> progressosDoNivel = nivel.getLicoes().stream()
                .map(l -> progressoRepository.findTopByUsuario_IdAndLessonIdOrderByConcluidoEmDesc(usuarioId, l.getId()))
                .collect(Collectors.toList());

        medalhaService.avaliarMedalhasDeNivel(usuarioId, progressosDoNivel);

        Level ultimoNivelDoCurso = levelRepository.findTopByOrderByOrdemDesc();
        boolean ehOUltimoNivel = nivel.getOrdem().equals(ultimoNivelDoCurso.getOrdem());
        if (ehOUltimoNivel) {
            medalhaService.avaliarMedalhaDeCurso(usuarioId);
        }
    }
}