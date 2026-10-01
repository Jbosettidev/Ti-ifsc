package com.example.demo.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServices usuarioServices;

    public UsuarioController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    public record AtualizarSenhaRequest(String senha, String novaSenha) {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criarUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioServices.salvar(usuario);
    }

    @GetMapping("/me")
    public Usuario meuUsuario(Authentication authentication) {
        return usuarioServices.buscarPorEmail(authentication.getName());
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id, Authentication authentication) {
        return usuarioServices.buscarDoUsuario(id, authentication.getName());
    }

    @PatchMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario dados, Authentication authentication) {
        return usuarioServices.atualizarParcial(id, dados, authentication.getName());
    }

    @PatchMapping("/{id}/seguranca")
    public Usuario atualizarSenha(@PathVariable Long id, @RequestBody AtualizarSenhaRequest req, Authentication authentication) {
        return usuarioServices.atualizarSenha(id, req.senha(), req.novaSenha(), authentication.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable Long id, Authentication authentication) {
        usuarioServices.deletar(id, authentication.getName());
    }
}