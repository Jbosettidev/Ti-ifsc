package com.example.demo.curso;

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
@Table(name = "curso")
public class Curso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 3, max = 200)
    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String descricao;

    @Size(max = 45)
    private String grauDificuldade;

    @Size(max = 45)
    private String statusAprovUsuario;

    @ManyToOne
    @JoinColumn(name = "medalha_id", nullable = false)
    private com.example.demo.medalha.Medalha medalha;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<com.example.demo.fase.Fase> fases;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<com.example.demo.progresso.Progresso> progressos;
}