package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    @GetMapping("/{pagina:^(?!api$)[a-zA-Z0-9-]+$}")
    public String carregarPagina(@PathVariable String pagina) {
        return "forward:/" + pagina + ".html";
    }
}