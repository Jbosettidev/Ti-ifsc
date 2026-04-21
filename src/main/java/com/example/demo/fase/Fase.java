package com.example.demo.fase;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private com.example.demo.curso.Curso curso;

    @OneToMany(mappedBy = "fase", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<com.example.demo.quiz.Quiz> quizzes;
}
//todo fazer relacionamento com usuario e o resto do arquivo do docs