package com.example.demo.user;
//controller recebe requisicoes e repostatas http pra aplicacao
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/usuarios") //cria o endpoint /usuarios
@RestController
public class UsuarioController { //TODO fazer o crud aqui

    @Autowired
    private UsuarioServices usuarioServices;

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario){ // requestBody obrigatorio se nn nao consegue pegar o corpo da requisicao
        return usuarioServices.salvar(usuario);
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioServices.buscar(id);
    }

    @GetMapping //pega todos os ids
    public List<Usuario> listarUsuarios() {
        return usuarioServices.listarTodos();
    }

    @PatchMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id,@RequestBody Usuario dados) {
        return usuarioServices.atualizarParcial(id, dados);
    }

    @DeleteMapping("/{id}") //deleta o usuario com tal id
    public void deletarUsusario(@PathVariable Long id){
        usuarioServices.deletar(id);
    }
}