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

    @PostMapping("/login") //new
    public  Usuario login(@RequestBody LoginRequest req) {
        try {
            return usuarioServices.autenticar(req.email(), req.senha());
        } catch (RuntimeException e) {
            return null;  // teste
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

    @PatchMapping("/{id}/seguranca") //new funcionando tomar cuidado com o return null
    public Usuario atualizarEmail(@PathVariable Long id, @RequestBody AtualizarEmailRequest req) {
        try {
            Usuario atualizado = usuarioServices.atualizarEmail(id, req.senha(), req.novoEmail());
            return atualizado;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioServices.deletar(id);
    }
}