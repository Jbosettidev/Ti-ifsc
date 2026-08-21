package com.example.demo.fase;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API REST do recurso {@link Fase} em {@code /fase}.
 * <p>
 * Endpoints CRUD e operação de negócio {@code PATCH .../concluir} para marcar fase como concluída.
 */
@RequestMapping("/fase")
@RestController
@RequiredArgsConstructor
public class FaseController {

    private final FaseServices faseServices;

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Fase> concluir(@PathVariable Long id) {
        return ResponseEntity.ok(faseServices.marcarComoConcluida(id));
    }

    @PostMapping
    public ResponseEntity<Fase> criarFase(@RequestBody Fase fase) {
        return ResponseEntity.ok(faseServices.salvar(fase));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fase> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(faseServices.buscar(id));
    }

    @GetMapping
    public ResponseEntity<List<Fase>> listarFase() {
        return ResponseEntity.ok(faseServices.listarTodos());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Fase> atualizar(@PathVariable Long id, @RequestBody Fase dados) {
        return ResponseEntity.ok(faseServices.atualizarParcial(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFase(@PathVariable Long id) {
        faseServices.deletar(id);
        return ResponseEntity.noContent().build();
    }
}