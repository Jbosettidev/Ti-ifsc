package com.example.demo.model;

import com.example.demo.user.Usuario;
import jakarta.persistence.*;
import java.time.LocalDateTime;

// Essa tabela guarda o RESULTADO de cada lição que um usuário terminou.
@Entity
public class UserLessonProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Antes: só um "Long userId" solto. Agora: ligação real com a
    // tabela de usuários (chave estrangeira de verdade).
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private Long lessonId;

    private Integer xpGanho;
    private Integer totalAcertos;
    private Integer totalPerguntas;

    private LocalDateTime concluidoEm;

    public double calcularAproveitamento() {
        if (totalPerguntas == null || totalPerguntas == 0) return 100.0;
        return (totalAcertos * 100.0) / totalPerguntas;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
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