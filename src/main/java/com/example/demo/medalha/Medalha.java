package com.example.demo.medalha;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Conquista ou distintivo (tabela {@code medalha}): nome obrigatório; critério e descrição
 * opcionais para explicar como a medalha é obtida.
 */
@Entity @Data @NoArgsConstructor
@Getter @Setter
@Table(name = "medalha")
public class Medalha {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 3, max = 100)
    @Column(nullable = false)
    private String nome;

    private String evento; // Evento necessário para desbloquear a medalha (ex: WIN_MATCH)

    private Integer alvo; // Quantidade necessária do evento para ganhar a medalha

    @Size(max = 150)
    private String descricao;

}