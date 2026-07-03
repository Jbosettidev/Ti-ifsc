package com.example.demo.medalha;

import com.example.demo.user.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "usuario_medalha", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "medalha_id"}))
public class UsuarioMedalha {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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