package com.example.demo.fase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/fase")
@RestController
public class FaseController { //todo precisa refazer com base no package do usuario

    @Autowired
    private FaseServices faseServices;

    @PatchMapping("/{id}/concluir")
    public Fase concluir(@PathVariable Long id) {
        return faseServices.marcarComoConcluida(id);
    }

    @PostMapping
    public Fase criarFase(@RequestBody Fase fase){ // requestBody obrigatorio se nn nao consegue pegar o corpo da requisicao
        return FaseServices.salvar(fase);
    }

    @GetMapping("/{id}")
    public Fase buscarPorId(@PathVariable Long id) {
        return faseServices.buscar(id);
    }

    @GetMapping //pega todos os ids
    public List<Fase> listarFase() {
        return faseServices.listarTodos();
    }

    @PatchMapping("/{id}")
    public Fase atualizar(@PathVariable Long id, @RequestBody Fase dados) {
        return faseServices.atualizarParcial(id, dados);
    }

    @DeleteMapping("/{id}") //deleta o usuario com tal id
    public void deletarFase(@PathVariable Long id){
        faseServices.deletar(id);
    }
}
