package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// Essa tabela guarda o RESULTADO de cada lição que um usuário terminou.
// É o histórico real, diferente do Lesson (que é só o "molde"/conteúdo).
@Entity
public class UserLessonProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long lessonId;

    private Integer xpGanho;
    private Integer totalAcertos;
    private Integer totalPerguntas;

    private LocalDateTime concluidoEm;

    // Esse método calcula o % de aproveitamento na hora, sem precisar
    // guardar ele pronto no banco (evita inconsistência se os números
    // mudarem depois).
    public double calcularAproveitamento() {
        if (totalPerguntas == null || totalPerguntas == 0) return 100.0;
        return (totalAcertos * 100.0) / totalPerguntas;
    }

    // --- Getters e Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getLessonId() { return lessonId; }
    public void setLessonId(Long lessonId) { this.lessonId = lessonId; }
    public Integer getXpGanho() { return xpGanho; }
    public void setXpGanho(Integer xpGanho) { this.xpGanho = xpGanho; }
    public Integer getTotalAcertos() { return totalAcertos; }
    public void setTotalAcertos(Integer totalAcertos) { this.totalAcertos = totalAcertos; }
    public Integer getTotalPerguntas() { return totalPerguntas; }
    public void setTotalPerguntas(Integer totalPerguntas) { this.totalPerguntas = totalPerguntas; }
    public LocalDateTime getConcluidoEm() { return concluidoEm; }
    public void setConcluidoEm(LocalDateTime concluidoEm) { this.concluidoEm = concluidoEm; }
}