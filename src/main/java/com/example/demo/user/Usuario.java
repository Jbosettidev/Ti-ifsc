package com.example.demo.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//configura o bd pra gerar e incrementar este valor a cada novo usuário.
    private Long id;

    @Column(nullable = false) //garante que o campo name nao seja vazio
    private String name;

    @Column(nullable = false,unique = true) //garante que seja unico e nao vazio
    private String email;

    @Column(nullable = false) //garante que nao seja vazio
    private String senha;

}
