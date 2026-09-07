package com.example.demo.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @NotBlank @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String nome;

    @NotBlank @Size(min = 7, max = 150)
    @Column(nullable = false, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank @Size(min = 8, max = 50)
    @Column(nullable = false)
    private String senha;

    @NotBlank
    @Column(nullable = false, unique = true) @Size(min = 5, max = 50)
    private String nomeusuario;

    @Min(0)
    private int xpTotal;

    // Removidos os campos "progressos" (Set<Progresso>) e "usuarioMedalhas"
    // (List<UsuarioMedalha>) — apontavam pras classes que você já apagou.
    //
    // Não recriei o equivalente aqui de propósito: o UserLessonProgress e
    // o UserMedalha já têm um @ManyToOne apontando PRA Usuario (rua de
    // mão única). Não precisamos do caminho de volta (Usuario -> lista de
    // progresso) a menos que algum tela precise "todo progresso desse
    // usuário" a partir do objeto Usuario — e mesmo aí, o jeito mais
    // seguro é buscar via UserLessonProgressRepository, não navegando
    // pela entidade (lembra do problema de loop infinito no JSON que
    // resolvemos lá no Level/Lesson? é o mesmo risco aqui).
}