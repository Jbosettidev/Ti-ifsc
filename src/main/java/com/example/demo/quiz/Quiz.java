package com.example.demo.quiz;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity @Data @NoArgsConstructor
@Getter @Setter
@Table(name = "quiz")
public class Quiz {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 3, max = 300)
    @Column(nullable = false)
    private String titulo;

    @Size(max = 45)
    private String pontuacaoMax;

    @ManyToOne
    @JoinColumn(name = "fase_id", nullable = false)
    private com.example.demo.fase.Fase fase;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<com.example.demo.questao.Questao> questoes;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<com.example.demo.progresso.Progresso> progressos;
}