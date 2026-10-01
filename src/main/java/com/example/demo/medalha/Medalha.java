package com.example.demo.medalha;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "medalha")
public class Medalha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    private String nome;

    private String evento;

    private Integer alvo;

    private boolean objConcluido;

    private String descricao;

    private String nomeArquivoIcone;
}