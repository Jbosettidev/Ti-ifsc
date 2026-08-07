package com.example.demo.medalha;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MedalhaController {

    private final MedalhaServices medalhaServices;

    @GetMapping("/medalhas")
    public ResponseEntity<List<Medalha>> listar() {
        return ResponseEntity.ok(medalhaServices.listarTodas());
    }

    @GetMapping("/medalhas/{id}")
    public ResponseEntity<Medalha> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medalhaServices.buscarPorId(id));
    }

    @PostMapping("/medalhas")
    public ResponseEntity<Medalha> criar(@RequestBody Medalha medalha) {
        return ResponseEntity.ok(medalhaServices.criar(medalha));
    }

    @PutMapping("/medalhas/{id}")
    public ResponseEntity<Medalha> atualizar(@PathVariable Long id, @RequestBody Medalha medalha) {
        return ResponseEntity.ok(medalhaServices.atualizar(id, medalha));
    }

    @DeleteMapping("/medalhas/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medalhaServices.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/usuarios/{usuarioId}/medalhas/{medalhaId}")
    public ResponseEntity<UsuarioMedalha> atualizarProgresso(
            @PathVariable Long usuarioId,
            @PathVariable Long medalhaId,
            @RequestParam Integer progresso) {
        return ResponseEntity.ok(medalhaServices.atualizarProgresso(usuarioId, medalhaId, progresso));
    }

    @GetMapping("/usuarios/{usuarioId}/medalhas")
    public ResponseEntity<List<UsuarioMedalha>> listarMedalhasDoUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(medalhaServices.listarMedalhasDoUsuario(usuarioId));
    }
    @GetMapping("/usuarios/{usuarioId}/medalhas/{medalhaId}")
    public ResponseEntity<UsuarioMedalha> buscarMedalhaDoUsuario(
            @PathVariable Long usuarioId,
            @PathVariable Long medalhaId) {
        return ResponseEntity.ok(medalhaServices.buscarMedalhaDoUsuario(usuarioId, medalhaId));
    }
}