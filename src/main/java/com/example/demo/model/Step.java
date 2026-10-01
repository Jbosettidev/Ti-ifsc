package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ordem; // posição do step dentro da lição (1, 2, 3...)

    // @Enumerated(EnumType.STRING) faz o banco salvar o texto "QUIZ", "TEXT"
    // etc, em vez de um número (0, 1, 2...). Isso é mais fácil de ler
    // direto no banco quando você for debugar.
    @Enumerated(EnumType.STRING)
    private StepType tipo;

    // Aqui mora o "conteúdo" de cada tela, guardado como um texto JSON puro.
    // @Lob (Large Object) diz ao banco "esse texto pode ser grande, não
    // limite a 255 caracteres como um VARCHAR normal".
    // columnDefinition = "TEXT" garante isso no MySQL/Postgres.
    //
    // Exemplo do que fica guardado aqui pra um step do tipo QUIZ:
    // {
    //   "pergunta": "O que torna a informação diferente de um bem físico?",
    //   "opcoes": ["Pode ser copiada sem se desgastar", "Perde valor com o tempo"],
    //   "correta": 0,
    //   "explicacao": "Informação pode ser copiada infinitas vezes sem se desgastar."
    // }
    @Lob
    @Column(columnDefinition = "TEXT")
    private String conteudoJson;

    // @JsonIgnore: evita loop Step -> Lesson -> Step
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    // --- Getters e Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getOrdem() { return ordem; }
    public void setOrdem(Integer ordem) { this.ordem = ordem; }

    public StepType getTipo() { return tipo; }
    public void setTipo(StepType tipo) { this.tipo = tipo; }

    public String getConteudoJson() { return conteudoJson; }
    public void setConteudoJson(String conteudoJson) { this.conteudoJson = conteudoJson; }

    public Lesson getLesson() { return lesson; }
    public void setLesson(Lesson lesson) { this.lesson = lesson; }
}