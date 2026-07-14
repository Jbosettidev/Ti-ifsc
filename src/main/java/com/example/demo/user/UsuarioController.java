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

    record AtualizarEmailRequest(String senha, String novoEmail) {} //serve pro transporte de dados

    record LoginRequest(String email, String senha) {}//serve pro transporte de dados

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) { //usa esse como base e muda tudo o resto pra aceitar o response
        return ResponseEntity.ok(usuarioServices.salvar(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginRequest req){
        return ResponseEntity.ok(usuarioServices.autenticar(req.email, req.senha));
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
    public ResponseEntity<Usuario> atualizarEmail(@PathVariable Long id, @RequestBody AtualizarEmailRequest req){
        return ResponseEntity.ok(usuarioServices.atualizarEmail(id, req.senha, req.novoEmail));
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioServices.deletar(id);
    }
}