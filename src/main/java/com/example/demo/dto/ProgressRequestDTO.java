package com.example.demo.dto;

// Formato do JSON que o licao-engine.js manda em POST /api/progress.
// "userId" aqui é temporário: hoje o frontend nem manda esse campo (o
// endpoint usa um valor padrão, veja o ProgressController). Assim que
// vocês tiverem login funcionando de verdade, troque isso por pegar o
// usuário logado direto no backend (via sessão/token) — NUNCA confie
// num userId que veio do navegador, porque dá pra qualquer pessoa
// forjar esse valor e mexer no progresso de outra conta.
public class ProgressRequestDTO {
    private Long lessonId;
    private Integer xpGanho;
    private Integer totalAcertos;
    private Integer totalPerguntas;

    public Long getLessonId() { return lessonId; }
    public void setLessonId(Long lessonId) { this.lessonId = lessonId; }
    public Integer getXpGanho() { return xpGanho; }
    public void setXpGanho(Integer xpGanho) { this.xpGanho = xpGanho; }
    public Integer getTotalAcertos() { return totalAcertos; }
    public void setTotalAcertos(Integer totalAcertos) { this.totalAcertos = totalAcertos; }
    public Integer getTotalPerguntas() { return totalPerguntas; }
    public void setTotalPerguntas(Integer totalPerguntas) { this.totalPerguntas = totalPerguntas; }
}