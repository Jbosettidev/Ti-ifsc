package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

// @Entity diz ao Spring: "essa classe vira uma tabela no banco de dados".
// O nome da tabela por padrão vira o nome da classe em minúsculo: "level".
@Entity
public class Level {

    // @Id marca o campo que é a chave primária (o identificador único da linha).
    // @GeneratedValue diz que o próprio banco gera esse número automaticamente
    // (1, 2, 3...) sempre que um novo Level é criado. Você nunca define esse valor na mão.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Um texto simples vira uma coluna VARCHAR no banco. Nada de especial aqui.
    private String titulo; // ex: "UNIDADE 1"

    private String descricao; // ex: "Domine os primeiros passos da segurança digital!"

    // A ordem em que esse nível aparece na lista (1, 2, 3, 4...)
    private Integer ordem;

    // @OneToMany diz: "um Level tem várias Lesson".
    // mappedBy = "level" diz que quem "é dono" da relação é o campo chamado
    // "level" lá dentro da classe Lesson (você vai ver isso no arquivo Lesson.java).
    // cascade = ALL significa: se eu salvar/apagar um Level, salva/apaga as
    // lições dele junto automaticamente.
    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL)
    private List<Lesson> licoes;

    // --- Getters e Setters ---
    // O Spring/Hibernate (e o JSON que vai pro frontend) precisa desses métodos
    // para conseguir ler e escrever os valores. É código repetitivo, mas
    // ferramentas como o Lombok (biblioteca) podem gerar isso pra você depois.

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getOrdem() { return ordem; }
    public void setOrdem(Integer ordem) { this.ordem = ordem; }

    public List<Lesson> getLicoes() { return licoes; }
    public void setLicoes(List<Lesson> licoes) { this.licoes = licoes; }
}