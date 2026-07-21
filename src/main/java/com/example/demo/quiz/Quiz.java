package com.example.demo.quiz;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Avaliação vinculada a uma {@link com.example.demo.fase.Fase}; contém questões e registros
 * de {@link com.example.demo.progresso.Progresso} dos usuários.
 */
@Entity @NoArgsConstructor
@Getter @Setter
@Table(name = "quiz")
public class Quiz {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

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