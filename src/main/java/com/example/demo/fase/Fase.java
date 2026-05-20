package com.example.demo.fase;

import com.example.demo.quiz.Quiz;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Etapa do fluxo de aprendizado (tabela {@code fase}): título único, descrição e flag de conclusão.
 * <p>
 * Relaciona-se com vários {@link Quiz} pelo campo {@code mappedBy = "fase"} em {@link Quiz#getFase()}.
 */
@Entity @Data @NoArgsConstructor
@Getter @Setter
@Table(name = "fase")
public class Fase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false,unique = true)
    private String titulo;

    @NotBlank @Column(nullable = false)
    private String descricao;

    private Boolean concluida;

    @OneToMany(mappedBy = "fase", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Quiz> quizzes;
}