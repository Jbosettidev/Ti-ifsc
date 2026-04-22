package com.example.demo.questao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Data @NoArgsConstructor
@Getter @Setter
@Table(name = "questao")
public class Questao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 3, max = 200)
    @Column(nullable = false)
    private String enunciado;

    @NotBlank @Size(min = 1, max = 600)
    @Column(nullable = false)
    private String respostaCorreta;

    @Size(max = 45)
    private String questaoCol; // Mantendo o nome original, mas pode ser renomeado para algo mais descritivo

    @Size(max = 700)
    private String alternativas;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false) // Relacionamento com Quiz
    private com.example.demo.quiz.Quiz quiz;
}