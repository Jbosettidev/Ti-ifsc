package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class ViewController {

    private static final Map<String, String> PAGINAS_ESPECIFICAS = Map.ofEntries(
            Map.entry("login", "TelasIniciais/login.html"),
            Map.entry("cadastro", "TelasIniciais/cadastro.html"),
            Map.entry("senha1", "TelasIniciais/senha1.html"),
            Map.entry("senha2", "TelasIniciais/senha2.html"),
            Map.entry("nova-senha", "TelasIniciais/nova-senha.html"),
            Map.entry("perfil", "perfil-usuario/perfil.html"),
            Map.entry("configuracoes", "perfil-usuario/configuracoes.html"),
            Map.entry("seguranca", "perfil-usuario/seguranca.html"),
            Map.entry("excluir", "perfil-usuario/excluir.html"),
            Map.entry("conquistas", "tela-conquistas/conquistas.html"),
            Map.entry("nivel-geral", "niveis/Nivel-Geral.html")
    );

    @GetMapping("/{pagina:^(?!api$)[a-zA-Z0-9-]+$}")
    public String carregarPagina(@PathVariable String pagina) {
        String caminho = PAGINAS_ESPECIFICAS.getOrDefault(pagina, pagina + ".html");
        return "forward:/" + caminho;
    }
}