package com.example.demo.user;

import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
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

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Progresso> progressos;

    // dentro de Usuario.java
    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties({"usuario", "hibernateLazyInitializer", "handler"})
    private List<UsuarioMedalha> usuarioMedalhas;
}