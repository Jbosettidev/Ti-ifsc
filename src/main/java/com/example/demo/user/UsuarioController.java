package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*") //libera a pagina web ter acesso a recursos restritos do webMvc
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    /**
     * Corpo JSON esperado em {@code PATCH /usuarios/{id}/seguranca}: senha atual para conferência
     * e o novo e-mail.
     */
    record AtualizarEmailRequest(String senha, String novoEmail) {
    } //serve pro transporte de dados

    record LoginRequest(String email, String senha) {
    }//serve pro transporte de dados

    /*//
    creio que seja desnecessario todos esses reposponseEntity pq ele ta puxando o package excessoes entt fica inutil, conferir
    */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            return ResponseEntity.ok(usuarioServices.autenticar(req.email(), req.senha()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Erro");//tirar pq da o log pro user
        }
    }

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(201).body("Usuario criado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao salvar");
        }
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioServices.buscar(id);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioServices.listarTodos();
    }

    @PatchMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario dados) {
        return usuarioServices.atualizarParcial(id, dados);
    }

    /**
     * Atualiza e-mail após validar senha; em erro de negócio retorna 400 com corpo textual
     * ({@code ResponseEntity<?>} permite corpo {@link Usuario} ou {@link String}).
     */
    @PatchMapping("/{id}/seguranca")
    public ResponseEntity<?> atualizarEmail(@PathVariable Long id, @RequestBody AtualizarEmailRequest req) {
        try {
            Usuario atualizado = usuarioServices.atualizarEmail(id, req.senha(), req.novoEmail());
            return ResponseEntity.ok("Redirecionando...");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro nos dados");
        }
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioServices.deletar(id);
    }
}