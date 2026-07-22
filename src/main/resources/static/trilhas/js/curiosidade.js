// curiosidade.js - renderiza blocos de "Você sabia?"
function renderCuriosidade(secao) {
    const section = document.createElement("section");
    section.className = "secao-curiosidade";

    const badge = document.createElement("span");
    badge.className = "curiosidade-badge";
    badge.textContent = "\uD83D\uDCA1 " + (secao.titulo || "Você sabia?");
    section.appendChild(badge);

    if (secao.texto) {
        const p = document.createElement("p");
        p.textContent = secao.texto;
        section.appendChild(p);
    }

    return section;
}

window.Renderers = window.Renderers || {};
window.Renderers.curiosidade = renderCuriosidade;
