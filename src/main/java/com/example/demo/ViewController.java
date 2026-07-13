package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Expõe páginas HTML estáticas com URL "limpa" (sem { .html} visível no navegador).

 * Para um GET { /perfil}, o método faz um forward interno para
 * { /perfil.html} (arquivos em { src/main/resources/static/}). O navegador
 * continua mostrando { /perfil}; não há nova requisição HTTP como em
 * { redirect:}, onde a URL mudaria para { /perfil.html}.

 * O padrão do path usa regex no { PathVariable}: aceita um segmento com letras,
 * números e hífen, mas exclui o valor exato {api}, para não
 * competir com endpoints REST tipicamente prefixados por { /api}.
 */
@Controller
public class ViewController {

    /**
     * @param pagina nome do arquivo sem extensão (ex.: {@code perfil} → {@code /perfil.html})
     * @return view name com prefixo {@code forward:} para o Spring despachar o recurso estático
     */
    @GetMapping("/{pagina:^(?!api$)[a-zA-Z0-9-]+$}")
    public String carregarPagina(@PathVariable String pagina) {
        return "forward:/" + pagina + ".html";
    }
}
