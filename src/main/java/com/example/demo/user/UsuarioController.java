package com.example.demo.user;

import com.example.demo.excessoes.GlobalExceptionHandler;
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

    record AtualizarEmailRequest(String senha, String novoEmail) {
    } //serve pro transporte de dados

    record LoginRequest(String email, String senha) {
    }//serve pro transporte de dados

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) { //usa esse como base e muda tudo o resto pra aceitar o response
        return ResponseEntity.ok(usuarioServices.salvar(usuario));
    }

    @PostMapping("/login") //new
    public Usuario login(@RequestBody LoginRequest req) {
        try {
            return usuarioServices.autenticar(req.email(), req.senha());
        } catch (RuntimeException e) {
            throw new RuntimeException("Dados incorretos");  // teste
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
            return usuarioServices.atualizarEmail(id, req.senha(), req.novoEmail());
        } catch (RuntimeException e) {
            throw new RuntimeException("Dados incorretos");
        }
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioServices.deletar(id);
    }
}