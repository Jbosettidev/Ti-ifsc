package com.example.demo.controller;

import com.example.demo.model.Lesson;
import com.example.demo.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// @RestController diz: "os métodos aqui dentro respondem em JSON, não em
// páginas HTML". @RequestMapping define o prefixo da URL: tudo aqui
// começa com /api/lessons.
@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    // @Autowired é o Spring "injetando" pra você uma instância pronta do
    // Repository — você não precisa fazer "new LessonRepository()" na mão,
    // o framework cuida disso.
    @Autowired
    private LessonRepository lessonRepository;

    // Isso responde a: GET http://localhost:8080/api/lessons/5
    // {id} na URL vira o parâmetro "id" do método, graças ao @PathVariable.
    @GetMapping("/{id}")
    public Lesson buscarLicao(@PathVariable Long id) {
        // findById devolve um "Optional" (pode ou não achar o registro).
        // orElseThrow lança um erro se o id não existir no banco.
        return lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lição não encontrada"));
    }

    // O Spring já transforma esse objeto Lesson (com a lista de Steps dentro)
    // em JSON sozinho. O frontend vai receber algo como:
    // {
    //   "id": 5,
    //   "titulo": "Fundamentos da Cibersegurança",
    //   "steps": [
    //     { "tipo": "TEXT", "conteudoJson": "{...}" },
    //     { "tipo": "QUIZ", "conteudoJson": "{...}" }
    //   ]
    // }
}