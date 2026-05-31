package com.example.demo.user;

import java.util.Set;
import com.example.demo.progresso.Progresso;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
public class Usuario {

    // Chave gerada pelo banco (auto incremento)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}