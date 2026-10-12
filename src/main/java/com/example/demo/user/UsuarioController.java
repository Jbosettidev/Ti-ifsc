package com.example.demo.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServices usuarioServices;

    public UsuarioController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    /** Tela de segurança: senha atual obrigatória; novaSenha e novoEmail são opcionais (pelo menos um). */
    public record AtualizarSegurancaRequest(String senha, String novaSenha, String novoEmail) {}

    /** Tela de exclusão: senha obrigatória; motivo e comentário são só para registro. */
    public record ExcluirContaRequest(String senha, String motivo, String comentario) {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criarUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioServices.salvar(usuario);
    }

    @GetMapping("/me")
    public Usuario meuUsuario(Authentication authentication) {
        return usuarioServices.buscarPorEmail(authentication.getName());
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id, Authentication authentication) {
        return usuarioServices.buscarDoUsuario(id, authentication.getName());
    }

    @PatchMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario dados, Authentication authentication) {
        return usuarioServices.atualizarParcial(id, dados, authentication.getName());
    }

    @PatchMapping("/{id}/seguranca")
    public Usuario atualizarSeguranca(@PathVariable Long id,
                                      @RequestBody AtualizarSegurancaRequest req,
                                      Authentication authentication,
                                      HttpServletRequest request) {
        String emailAntigo = authentication.getName();
        Usuario usuario = usuarioServices.atualizarSeguranca(
                id, req.senha(), req.novaSenha(), req.novoEmail(), emailAntigo);

        // Credenciais mudaram: encerra a sessão para exigir novo login com os dados atualizados.
        boolean emailMudou = !usuario.getEmail().equalsIgnoreCase(emailAntigo);
        boolean senhaMudou = req.novaSenha() != null && !req.novaSenha().isBlank();
        if (emailMudou || senhaMudou) {
            encerrarSessao(request);
        }
        return usuario;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable Long id,
                               @RequestBody ExcluirContaRequest req,
                               Authentication authentication,
                               HttpServletRequest request) {
        usuarioServices.deletar(id, req.senha(), req.motivo(), req.comentario(), authentication.getName());
        encerrarSessao(request); // a conta não existe mais, então a sessão também acaba
    }

    private void encerrarSessao(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}