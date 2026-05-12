package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;  //meio obvio
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // aqui mantem o direcionamento dos dados
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*") // deixar obrigatorio pra poder mecher no front
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices; // chama a classe services

    record AtualizarEmailRequest(String senha, String novoEmail) { //corpo da requisicao deve ser json
    } // é uma forma curta de declarar uma classe que só carrega dados,, basicamnte uma classe

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) { // request e obrigatoio pra poder puxar os dados
        return usuarioServices.salvar(usuario);
    }

    @GetMapping("/{id}") // pega o email pra buscar
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioServices.buscar(id);
    }

    @GetMapping // lista os user pro front
    public List<Usuario> listarUsuarios() {
        return usuarioServices.listarTodos();
    }

    @PatchMapping("/{id}") // attualizar parte de tal coisa pegando o id
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario dados) {
        return usuarioServices.atualizarParcial(id, dados);
    }

    @PatchMapping("/{id}/seguranca") //expoe o endpoint pra alterar o email // o <?> é pra nao precisar especificar o tipo de retorno
    public ResponseEntity<?> atualizarEmail(@PathVariable Long id, @RequestBody AtualizarEmailRequest req) {
        try {
            Usuario atualizado = usuarioServices.atualizarEmail(id, req.senha(), req.novoEmail());
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}") // deltar id de acordo com o id do user
    public void deletarUsuario(@PathVariable Long id) {
        usuarioServices.deletar(id);
    }
}