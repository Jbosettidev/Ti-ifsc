package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    // Mapeamento específico para páginas principais
    @GetMapping("/login")
    public String login() {
        return "forward:/TelasIniciais/login.html";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "forward:/TelasIniciais/cadastro.html";
    }

    @GetMapping("/home")
    public String home() {
        return "forward:/TelasIniciais/index.html";
    }

    @GetMapping("/nova-senha")
    public String novaSenha() {
        return "forward:/TelasIniciais/nova-senha.html";
    }

    // Mapeamento genérico para outras páginas
    @GetMapping("/{pagina:^(?!api$|login$|cadastro$|home$|senha1$|nova-senha$)[a-zA-Z0-9-]+$}")
    public String carregarPagina(@PathVariable String pagina) {
        return "forward:/TelasIniciais/" + pagina + ".html";
    }
}