package com.example.demo.controller;

import com.example.demo.excessoes.ResourceNotFoundException;
import com.example.demo.model.Lesson;
import com.example.demo.model.Step;
import com.example.demo.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController diz: "os métodos aqui dentro respondem em JSON, não em
// páginas HTML". @RequestMapping define o prefixo da URL: tudo aqui
// começa com /api/lessons.
@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    // GET http://localhost:8080/api/lessons/5
    @GetMapping("/{id}")
    public Lesson buscarLicao(@PathVariable Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lição não encontrada"));
    }

    // GET http://localhost:8080/api/lessons/5/steps
    // É essa a URL que o licao-engine.js chama. Os steps já vêm ordenados
    // pelo @OrderBy("ordem ASC") da entidade Lesson.
    // Novo método: sem ele o front recebia 404 e a lição ficava em branco.
    @GetMapping("/{id}/steps")
    public ResponseEntity<List<Step>> buscarSteps(@PathVariable Long id) {
        return ResponseEntity.ok(buscarLicao(id).getSteps());
    }
}