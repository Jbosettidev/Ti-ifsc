package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    record AtualizarEmailRequest(String senha, String novoEmail) {}

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

    @PatchMapping("/{id}/seguranca")
    public ResponseEntity<?> atualizarEmail(@PathVariable Long id,
                                            @RequestBody AtualizarEmailRequest req) {
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