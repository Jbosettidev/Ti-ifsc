package com.example.demo.progresso;

import com.example.demo.fase.Fase;
import com.example.demo.quiz.Quiz;
import com.example.demo.user.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Registro de avanço de um {@link Usuario} em uma {@link Fase} e {@link Quiz}: percentual,
 * pontuação e chaves estrangeiras para consultas e relatórios.
 */
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
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fase_id", nullable = false)
    private Fase fase;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
}