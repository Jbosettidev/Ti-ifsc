package com.example.demo.progresso;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Data @NoArgsConstructor
@Getter @Setter
@Table(name = "progresso")
public class Progresso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float percentualConclusao;

    @Min(0)
    private Integer pontuacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private com.example.demo.user.Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private com.example.demo.curso.Curso curso;

    @ManyToOne
    @JoinColumn(name = "fase_id", nullable = false)
    private com.example.demo.fase.Fase fase;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private com.example.demo.quiz.Quiz quiz;
}