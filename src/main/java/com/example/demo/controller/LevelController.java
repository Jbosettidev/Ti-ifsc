package com.example.demo.controller;

import com.example.demo.dto.LevelSummaryDTO;
import com.example.demo.dto.LessonSummaryDTO;
import com.example.demo.model.Level;
import com.example.demo.repository.LevelRepository;
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

    // Isso responde: GET http://localhost:8080/api/levels
    // Devolve TODOS os níveis, cada um já com a lista de lições dentro
    // (sem os steps — isso só vem depois, quando abre a lição de verdade).
    @GetMapping
    public List<LevelSummaryDTO> listarNiveis() {
        List<Level> niveis = levelRepository.findAll();

        // .stream() + .map() é o jeito Java de fazer "para cada item da
        // lista, transforma nesse outro formato e devolve uma lista nova".
        // Aqui a gente converte cada Level (entidade do banco) num
        // LevelSummaryDTO (formato seguro pra virar JSON).
        return niveis.stream()
                .sorted(Comparator.comparing(Level::getOrdem))
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    // Isso responde: GET http://localhost:8080/api/levels/1
    // Usado pela tela nivel.html, que só precisa dos dados de UM nível.
    @GetMapping("/{id}")
    public LevelSummaryDTO buscarNivel(@PathVariable Long id) {
        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nível não encontrado"));
        return paraDTO(level);
    }

    private LevelSummaryDTO paraDTO(Level level) {
        List<LessonSummaryDTO> licoesDTO = level.getLicoes().stream()
                .sorted(Comparator.comparing(l -> l.getOrdem()))
                .map(licao -> new LessonSummaryDTO(
                        licao.getId(),
                        licao.getTitulo(),
                        licao.getOrdem(),
                        licao.getXpTotal(),
                        licao.isDesafioFinal(),
                        false // TODO: Passo 6 - calcular com base no progresso do usuário
                ))
                .collect(Collectors.toList());

        return new LevelSummaryDTO(
                level.getId(),
                level.getTitulo(),
                level.getDescricao(),
                level.getOrdem(),
                false, // TODO: Passo 6 - calcular com base no progresso do usuário
                licoesDTO
        );
    }
}