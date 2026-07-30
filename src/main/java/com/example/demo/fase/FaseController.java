package com.example.demo.fase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API REST do recurso {@link Fase} em {@code /fase}.
 * <p>
 * Endpoints CRUD e operação de negócio {@code PATCH .../concluir} para marcar fase como concluída.
 */
@RequestMapping("/fase")
@RestController
public class FaseController {

    @Autowired
    private FaseServices faseServices;

    @PatchMapping("/{id}/concluir")
    public Fase concluir(@PathVariable Long id) {
        return faseServices.marcarComoConcluida(id);
    }

    @PostMapping
    public Fase criarFase(@RequestBody Fase fase) {
        return faseServices.salvar(fase);
    }

    @GetMapping("/{id}")
    public Fase buscarPorId(@PathVariable Long id) {
        return faseServices.buscar(id);
    }

    @GetMapping
    public List<Fase> listarFase() {
        return faseServices.listarTodos();
    }

    @PatchMapping("/{id}")
    public Fase atualizar(@PathVariable Long id, @RequestBody Fase dados) {
        return faseServices.atualizarParcial(id, dados);
    }

    @DeleteMapping("/{id}")
    public void deletarFase(@PathVariable Long id) {
        faseServices.deletar(id);
    }
}