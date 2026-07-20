package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Expõe páginas HTML estáticas com URL "limpa" (sem {@code .html} visível no navegador).
 * <p>
 * Para um GET {@code /perfil}, o método faz um <strong>forward</strong> interno para
 * {@code /perfil.html} (arquivos em {@code src/main/resources/static/}). O navegador
 * continua mostrando {@code /perfil}; não há nova requisição HTTP como em
 * {@code redirect:}, onde a URL mudaria para {@code /perfil.html}.
 * <p>
 * O padrão do path usa regex no {@link PathVariable}: aceita um segmento com letras,
 * números e hífen, mas <strong>exclui</strong> o valor exato {@code api}, para não
 * competir com endpoints REST tipicamente prefixados por {@code /api}.
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
