package com.example.demo.dto;

// "DTO" = Data Transfer Object. É uma classe que existe só pra moldar
// a resposta da API do jeito que o frontend precisa — nunca é salva
// no banco, é só uma "caixa" temporária de dados.
//
// Repare que ela NÃO tem a lista de Steps (isso só é buscado quando o
// usuário abre a lição de verdade, via GET /api/lessons/{id}/steps)
// e NÃO tem referência de volta pro Level — é isso que evita o loop.
public class LessonSummaryDTO {

    private Long id;
    private String titulo;
    private Integer ordem;
    private Integer xpTotal;
    private boolean desafioFinal;
    private boolean bloqueada; // por enquanto sempre false; entra na regra no Passo 6

    public LessonSummaryDTO(Long id, String titulo, Integer ordem, Integer xpTotal,
                             boolean desafioFinal, boolean bloqueada) {
        this.id = id;
        this.titulo = titulo;
        this.ordem = ordem;
        this.xpTotal = xpTotal;
        this.desafioFinal = desafioFinal;
        this.bloqueada = bloqueada;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public Integer getOrdem() { return ordem; }
    public Integer getXpTotal() { return xpTotal; }
    public boolean isDesafioFinal() { return desafioFinal; }
    public boolean isBloqueada() { return bloqueada; }
}