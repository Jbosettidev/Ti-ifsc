package com.example.demo.medalha;

import com.example.demo.user.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "medalha")
public class Medalha {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 3, max = 100)
    @Column(nullable = false)
    private String nome;

    private String evento;

    private Integer alvo;

    private boolean objConcluido;

    @Size(max = 150)
    private String descricao;

    // A ligação fica do lado "Muitos" (Muitas medalhas pertencem a Um usuário)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}