package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * API REST de usuários sob o prefixo {@code /usuarios}.
 * <p>
 * {@code @RestController} serializa retornos (JSON por padrão) e combina
 * {@code @Controller} com {@code @ResponseBody} nos métodos.
 * {@code @CrossOrigin(origins = "*")} libera chamadas do front em outra origem (CORS);
 * em produção costuma-se restringir a origens conhecidas.
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    /**
     * Corpo JSON esperado em {@code PATCH /usuarios/{id}/seguranca}: senha atual para conferência
     * e o novo e-mail.
     */
    record AtualizarEmailRequest(String senha, String novoEmail) {
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioServices.salvar(usuario);
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
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioServices.deletar(id);
    }
}