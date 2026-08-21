package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*") //libera a pagina web ter acesso a recursos restritos do webMvc
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    record AtualizarEmailRequest(String senha, String novoEmail) {
    } //serve pro transporte de dados

    record LoginRequest(String email, String senha) {
    }//serve pro transporte de dados

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioServices.salvar(usuario);
    }

    @PostMapping("/login") //new
    public Usuario login(@RequestBody LoginRequest req) {
        return usuarioServices.autenticar(req.email(), req.senha());
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

   record AtualizarSenhaRequest(String senha, String novaSenha) {
}

    @PatchMapping("/{id}/seguranca")
        public Usuario atualizarSenha(@PathVariable Long id, @RequestBody AtualizarSenhaRequest req) {
            return usuarioServices.atualizarSenha(id, req.senha(), req.novaSenha());
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioServices.deletar(id);
    }
}