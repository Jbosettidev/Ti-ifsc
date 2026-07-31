// cards.js - renderiza grupos de cards (ex.: comparativos, resumos)
function renderCards(secao) {
    const section = document.createElement("section");
    section.className = "secao-cards";

    if (secao.titulo) {
        const h2 = document.createElement("h2");
        h2.textContent = secao.titulo;
        section.appendChild(h2);
    }

    const wrapper = document.createElement("div");
    wrapper.className = secao.wrapper_classe || "grid-cards";

    (secao.filhos || []).forEach((filho) => {
        const el = document.createElement(filho.tipo === "div" ? "div" : (filho.tipo || "div"));
        el.classList.add("card");
        if (filho.classe) el.classList.add(filho.classe);

        if (filho.titulo) {
            const h3 = document.createElement("h3");
            h3.textContent = filho.titulo;
            el.appendChild(h3);
        }

        if (filho.texto) {
            const p = document.createElement("p");
            p.textContent = filho.texto;
            el.appendChild(p);
        }

        wrapper.appendChild(el);
    });

    section.appendChild(wrapper);
    return section;
}

window.Renderers = window.Renderers || {};
window.Renderers.cards = renderCards;
