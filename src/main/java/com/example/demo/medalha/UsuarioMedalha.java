package com.example.demo.medalha;

import com.example.demo.user.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "usuario_medalha", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "medalha_id"}))
public class UsuarioMedalha {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnoreProperties({"medalhas", "usuarioMedalhas", "hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "medalha_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Medalha medalha;

    private Integer progresso;
    private boolean concluida;
}