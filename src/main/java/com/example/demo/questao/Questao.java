package com.example.demo.questao;

import com.example.demo.quiz.Quiz;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Questão de um {@link Quiz}: enunciado, resposta esperada, metadados opcionais e alternativas
 * (ex.: JSON ou texto serializado, conforme o front).
 */
@Entity @NoArgsConstructor
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

    /** Coluna auxiliar legada / genérica; considerar renomear no schema para nome semântico. */
    @Size(max = 45)
    private String questaoCol;

    @Size(max = 700)
    private String alternativas;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
}