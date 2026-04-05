package com.example.demo.user;
//parte que vai pro banco de dados,, aqui cria as entidades pra usar no sql e java
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Data @NoArgsConstructor
@Getter @Setter
@Table(name = "usuario")
public class Usuario { //essa ta no /cyber do properties

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//configura o bd pra gerar e incrementar este valor a cada novo usuário.
    private Long id; //gera um id unico e que se autoincrementa

    @NotBlank @Size(min = 3, max = 50) //vem do pacote validation
    @Column(nullable = false)// nao deixa ser vazio
    private String nome;

    @NotBlank @Size(min = 7, max = 150)
    @Column(nullable = false,unique = true) //garante que seja unico e nao vazio
    private String email;

    @NotBlank @Size(min = 8, max = 50)
    @Column(nullable = false) //garante que nao seja vazio
    private String senha;

    @NotBlank
    @Column(nullable = false,unique = true) @Size(min = 5, max = 50)
    private String nomeusuario; // arrumar pra nomeUsuario no banco e aqui dps

    @Min(0)//nn aceita valor negativo
    private int xpTotal; //sempre inicializa como zero

}