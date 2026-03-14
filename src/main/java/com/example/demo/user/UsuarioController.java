package com.example.demo.user;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    private final UsuarioServices usuarioServices;
    public UsuarioController(UsuarioServices usuarioServices){
        this.usuarioServices = usuarioServices;
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        return usuarioServices.salvar(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioServices.listarTodos();
    }
}
