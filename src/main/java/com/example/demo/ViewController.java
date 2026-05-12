package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home() {
        return "redirect:/TelasIniciais/index.html";
    }
    @GetMapping("/{pagina:^(?!api$)[a-zA-Z0-9-]+$}")
    public String carregarPagina(@PathVariable String pagina) {
        return "forward:/" + pagina + ".html"; //todo lembrar de colocar as outra formas de redirecionamento de paginas 
    }
}
/*
forward: (mesma requisição, URL não muda) “fazer as coisas parecer uma única página”. Útil para não ter quefazer requisições para outras páginas.

redirect: (nova requisição, URL muda) Útil após login, “atalhos” amigáveis ou mandar o usuário para outra pasta. 
*/