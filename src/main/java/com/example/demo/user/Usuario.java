package com.example.demo.user;
//parte que vai pro banco de dados,, aqui cria as entidades pra usar no sql e java
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
public class Usuario { //acho q tem que colocar o nome da table,, nn sei

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//configura o bd pra gerar e incrementar este valor a cada novo usuário.
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco") @Size(min = 3, max = 100) //vem do validation,, usa dps pra email e senha
    @Column(nullable = false)// nao deixa ser vazio
    private String name;

   @Column(nullable = false,unique = true) //garante que seja unico e nao vazio
    private String email;

    @Column(nullable = false) //garante que nao seja vazio
    private String senha;

    //alguem confira o bd e faca o teste do notblank,, o resto funciona tranquilo
}
