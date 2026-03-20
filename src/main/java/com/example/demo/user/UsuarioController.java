package com.example.demo.user;
//controller recebe requisicoes e repostatas http pra aplicacao
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/usuarios") //cria o endpoint /usuarios
@RestController
public class UsuarioController { //TODO fazer o crud aqui

    private final UsuarioServices usuarioServices; // esse bloco e a msm coisa do autowired mas e menos chance de causar erro
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
