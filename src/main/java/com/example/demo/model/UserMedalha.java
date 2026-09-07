package com.example.demo.model;

import com.example.demo.user.Usuario;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserMedalha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mesma troca do UserLessonProgress: Long userId solto -> ligação
    // real com a tabela de usuários.
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Medalha medalha;

    private LocalDateTime conquistadaEm;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Medalha getMedalha() { return medalha; }
    public void setMedalha(Medalha medalha) { this.medalha = medalha; }
    public LocalDateTime getConquistadaEm() { return conquistadaEm; }
    public void setConquistadaEm(LocalDateTime conquistadaEm) { this.conquistadaEm = conquistadaEm; }
}