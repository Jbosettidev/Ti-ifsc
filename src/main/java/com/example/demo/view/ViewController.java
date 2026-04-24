package com.example.demo.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller // Não é @RestController, pois retorna um nome de view/recurso
public class ViewController {

    /**
     * Rota para a página inicial (index.html)
     */
    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }

    /**
     * Rota "Coringa": Qualquer nome que você digitar na URL que não seja uma API
     * o Spring vai tentar encontrar um arquivo .html correspondente na pasta static.
     * Exemplo: /perfil -> procura static/perfil.html
     * Exemplo: /usuarios -> procura static/usuarios.html
     */
    @GetMapping("/{pagina}")
    public String carregarPagina(@PathVariable String pagina) {
        // Evita que o Spring tente processar arquivos que já tem extensão ou chamadas de API
        if (pagina.contains(".") || pagina.equals("api")) { //todo mudar isso qq ta errado ai bros
            return null;
        }
        return "forward:/" + pagina + ".html";
    }

}
//manus ia gerou

/*
 * Ao usar "forward:/index.html", o Spring busca o arquivo dentro da pasta 'static'
 * automaticamente, sem precisar de tod0 o caminho "src/main/resources...".
 * Se você estiver tentando redirecionar de outra página (como perfil) via Java:
 * Use "redirect:/" para que o navegador faça uma nova requisição para a raiz.*/