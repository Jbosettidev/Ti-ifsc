package com.example.demo.user;

import com.example.demo.excessoes.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    // Records para transporte de dados
    record AtualizarEmailRequest(String senha, String novoEmail) {}
    record LoginRequest(String email, String senha) {}

    @PostMapping
    public ResponseEntity<ApiResponse> criar(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioServices.salvar(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(
                        HttpStatus.CREATED.value(),
                        "Usuário criado com sucesso",
                        "/usuarios/" + usuarioSalvo.getId(),
                        usuarioSalvo.getId()  // ← AGORA FUNCIONA!
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest req) {
        Usuario usuario = usuarioServices.autenticar(req.email, req.senha);

        return ResponseEntity.ok(
                new ApiResponse(
                        HttpStatus.OK.value(),
                        "Login realizado com sucesso",
                        "/usuarios/" + usuario.getId(),
                        usuario.getId()  // ← AGORA FUNCIONA!
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioServices.buscar(id));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioServices.listarTodos());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> atualizarParcial(
            @PathVariable Long id,
            @Valid @RequestBody Usuario dados) {

        Usuario usuarioAtualizado = usuarioServices.atualizarParcial(id, dados);

        return ResponseEntity.ok(
                new ApiResponse(
                        HttpStatus.OK.value(),
                        "Usuário atualizado com sucesso",
                        "/usuarios/" + id
                )
        );
    }

    @PatchMapping("/{id}/seguranca")
    public ResponseEntity<ApiResponse> atualizarEmail(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarEmailRequest req) {

        Usuario usuarioAtualizado = usuarioServices.atualizarEmail(id, req.senha, req.novoEmail);

        return ResponseEntity.ok(
                new ApiResponse(
                        HttpStatus.OK.value(),
                        "Email atualizado com sucesso",
                        "/usuarios/" + id
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletarUsuario(@PathVariable Long id) {
        usuarioServices.deletar(id);

        return ResponseEntity.ok(
                new ApiResponse(
                        HttpStatus.OK.value(),
                        "Usuário deletado com sucesso",
                        "/usuarios/" + id
                )
        );
    }
}