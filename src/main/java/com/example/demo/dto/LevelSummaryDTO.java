package com.example.demo.dto;

import java.util.List;

// Repare que o formato aqui já fica bem parecido com o que seu script.js
// atual espera de "trilha": numero, titulo, descricao, missoes.
// Só que aqui eu chamo de "ordem" e "licoes" (nomes que já usamos nas
// entidades) — no Passo 2 a gente decide se muda o nome no JS ou aqui.
public class LevelSummaryDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private Integer ordem;
    private boolean bloqueado; // por enquanto sempre false; entra na regra no Passo 6
    private List<LessonSummaryDTO> licoes;

    public LevelSummaryDTO(Long id, String titulo, String descricao, Integer ordem,
                            boolean bloqueado, List<LessonSummaryDTO> licoes) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.ordem = ordem;
        this.bloqueado = bloqueado;
        this.licoes = licoes;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public Integer getOrdem() { return ordem; }
    public boolean isBloqueado() { return bloqueado; }
    public List<LessonSummaryDTO> getLicoes() { return licoes; }
}