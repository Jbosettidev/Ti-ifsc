package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo; // ex: "Fundamentos da Cibersegurança"

    private Integer ordem; // posição da lição dentro do nível (1, 2, 3...)

    private Integer xpTotal; // quanto XP essa lição vale no total (ex: 30)

    // Se essa lição É o desafio final do nível, marcamos aqui.
    // Assim o frontend sabe: "essa lição é diferente, é o teste final".
    private boolean desafioFinal;

    // @ManyToOne diz: "várias Lesson pertencem a UM Level".
    // @JoinColumn cria a coluna "level_id" na tabela lesson, que guarda
    // o id do nível dono dessa lição — é assim que o banco liga as tabelas.
    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    // Uma lição tem vários steps (telas). orderBy garante que, ao buscar,
    // eles já venham na ordem certa (1, 2, 3...) sem você ter que ordenar na mão.
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    @OrderBy("ordem ASC")
    private List<Step> steps;

    // --- Getters e Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Integer getOrdem() { return ordem; }
    public void setOrdem(Integer ordem) { this.ordem = ordem; }

    public Integer getXpTotal() { return xpTotal; }
    public void setXpTotal(Integer xpTotal) { this.xpTotal = xpTotal; }

    public boolean isDesafioFinal() { return desafioFinal; }
    public void setDesafioFinal(boolean desafioFinal) { this.desafioFinal = desafioFinal; }

    public Level getLevel() { return level; }
    public void setLevel(Level level) { this.level = level; }

    public List<Step> getSteps() { return steps; }
    public void setSteps(List<Step> steps) { this.steps = steps; }
}