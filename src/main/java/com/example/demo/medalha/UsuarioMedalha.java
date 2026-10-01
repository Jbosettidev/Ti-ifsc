package com.example.demo.medalha;

import com.example.demo.user.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entidade de junção Usuario x Medalha. Existe (e não um @ManyToMany) porque a relação
 * tem dados próprios: progresso e se foi concluída.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usuario_medalha",
        uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "medalha_id"}))
public class UsuarioMedalha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Não vai no JSON: a URL já diz de quem é (/usuarios/{id}/medalhas).
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "medalha_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Medalha medalha;

    private Integer progresso;

    private boolean concluida;
}
