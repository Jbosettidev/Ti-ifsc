package com.example.demo.user;

import com.example.demo.progresso.Progresso;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Entidade JPA {@code usuario}: mapeia linhas da tabela homônima e define relacionamentos.
 * <p>
 * {@code @Entity} marca a classe para o Hibernate; {@code @Table} fixa o nome da tabela.
 * Anotações {@code jakarta.validation} são usadas com {@code @Valid} no controller para
 * validar entrada antes de persistir. {@code @OneToMany(mappedBy = "usuario")} indica que
 * o lado "dono" do relacionamento está em {@link Progresso#getUsuario()}; {@code cascade}
 * propaga operações; {@code orphanRemoval} remove progressos órfãos se forem desassociados.
 */
@Entity @Data @NoArgsConstructor
@Getter @Setter
@Table(name = "usuario")
public class Usuario {

    /** Chave surrogate gerada pelo banco (auto incremento). */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String nome;

    @NotBlank @Size(min = 7, max = 150)
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank @Size(min = 8, max = 50)
    @Column(nullable = false)
    private String senha;

    @NotBlank
    @Column(nullable = false, unique = true) @Size(min = 5, max = 50)
    private String nomeusuario;

    @Min(0)
    private int xpTotal;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Progresso> progressos;
}