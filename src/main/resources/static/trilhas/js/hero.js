// hero.js - renderiza a seção de abertura de cada missão
function renderHero(secao) {
    const section = document.createElement("section");
    section.className = "secao-hero";

    if (secao.titulo) {
        const h1 = document.createElement("h1");
        h1.textContent = secao.titulo;
        section.appendChild(h1);
    }

    if (secao.texto) {
        const p = document.createElement("p");
        p.textContent = secao.texto;
        section.appendChild(p);
    }

    return section;
}

window.Renderers = window.Renderers || {};
window.Renderers.hero = renderHero;
