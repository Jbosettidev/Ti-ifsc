package com.example.demo.controller;

import com.example.demo.dto.LevelSummaryDTO;
import com.example.demo.dto.LessonSummaryDTO;
import com.example.demo.model.Level;
import com.example.demo.model.Lesson;
import com.example.demo.repository.LevelRepository;
import com.example.demo.repository.UserLessonProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/levels")
public class LevelController {

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private UserLessonProgressRepository progressoRepository;

    // Só o nome do parâmetro mudou (userId -> usuarioId), pra acompanhar
    // a mesma troca que fizemos no resto do backend.
    @GetMapping
    public List<LevelSummaryDTO> listarNiveis(@RequestParam(defaultValue = "1") Long usuarioId) {
        List<Level> niveis = levelRepository.findAll().stream()
                .sorted(Comparator.comparing(Level::getOrdem))
                .collect(Collectors.toList());

        List<LevelSummaryDTO> resultado = new java.util.ArrayList<>();
        Level nivelAnterior = null;

        for (Level nivel : niveis) {
            boolean bloqueado = nivelAnterior != null && !todasLicoesConcluidas(usuarioId, nivelAnterior);
            resultado.add(paraDTO(nivel, usuarioId, bloqueado));
            nivelAnterior = nivel;
        }

        return resultado;
    }

    @GetMapping("/{id}")
    public LevelSummaryDTO buscarNivel(@PathVariable Long id,
                                        @RequestParam(defaultValue = "1") Long usuarioId) {
        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nível não encontrado"));

        boolean bloqueado = levelRepository.findAll().stream()
                .filter(l -> l.getOrdem().equals(level.getOrdem() - 1))
                .findFirst()
                .map(anterior -> !todasLicoesConcluidas(usuarioId, anterior))
                .orElse(false);

        return paraDTO(level, usuarioId, bloqueado);
    }

    private boolean todasLicoesConcluidas(Long usuarioId, Level nivel) {
        return nivel.getLicoes().stream()
                .allMatch(l -> progressoRepository.existsByUsuario_IdAndLessonId(usuarioId, l.getId()));
    }

    private LevelSummaryDTO paraDTO(Level level, Long usuarioId, boolean nivelBloqueado) {
        List<Lesson> licoesOrdenadas = level.getLicoes().stream()
                .sorted(Comparator.comparing(Lesson::getOrdem))
                .collect(Collectors.toList());

        List<LessonSummaryDTO> licoesDTO = new java.util.ArrayList<>();
        for (int i = 0; i < licoesOrdenadas.size(); i++) {
            Lesson licao = licoesOrdenadas.get(i);

            boolean licaoBloqueada;
            if (nivelBloqueado) {
                licaoBloqueada = true;
            } else if (i == 0) {
                licaoBloqueada = false;
            } else {
                Lesson anterior = licoesOrdenadas.get(i - 1);
                licaoBloqueada = !progressoRepository.existsByUsuario_IdAndLessonId(usuarioId, anterior.getId());
            }

            licoesDTO.add(new LessonSummaryDTO(
                    licao.getId(),
                    licao.getTitulo(),
                    licao.getOrdem(),
                    licao.getXpTotal(),
                    licao.isDesafioFinal(),
                    licaoBloqueada
            ));
        }

        return new LevelSummaryDTO(
                level.getId(),
                level.getTitulo(),
                level.getDescricao(),
                level.getOrdem(),
                nivelBloqueado,
                licoesDTO
        );
    }
}