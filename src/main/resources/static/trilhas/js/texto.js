// texto.js - renderiza blocos de texto simples (com título opcional)
function renderTexto(secao) {
    const section = document.createElement("section");
    section.className = "secao-texto";
    if (secao.classe) section.classList.add(secao.classe);

    if (secao.titulo) {
        const h2 = document.createElement("h2");
        h2.textContent = secao.titulo;
        section.appendChild(h2);
    }

    if (secao.texto) {
        const p = document.createElement("p");
        p.textContent = secao.texto;
        section.appendChild(p);
    }

    return section;
}

window.Renderers = window.Renderers || {};
window.Renderers.texto = renderTexto;
