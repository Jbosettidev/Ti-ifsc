// conclusao.js - renderiza o fechamento de missão, de trilha e do curso
function renderConclusao(secao) {
    const section = document.createElement("section");
    section.className = "secao-conclusao";

    const h2 = document.createElement("h2");
    h2.textContent = "Resumo da missão";
    section.appendChild(h2);

    const ul = document.createElement("ul");
    (secao.resumo || []).forEach((item) => {
        const li = document.createElement("li");
        li.textContent = item;
        ul.appendChild(li);
    });
    section.appendChild(ul);

    if (secao.xp) {
        const xp = document.createElement("p");
        xp.className = "conclusao-xp";
        xp.textContent = "+" + secao.xp + " XP";
        section.appendChild(xp);
    }

    return section;
}

function renderConclusaoTrilha(secao) {
    const section = document.createElement("section");
    section.className = "secao-conclusao-trilha";

    const h2 = document.createElement("h2");
    h2.textContent = "\uD83C\uDF89 " + secao.titulo;
    section.appendChild(h2);

    if (secao.texto) {
        const p = document.createElement("p");
        p.textContent = secao.texto;
        section.appendChild(p);
    }

    if (secao.proximaTrilha) {
        const proxima = document.createElement("p");
        proxima.className = "proxima-trilha";
        proxima.textContent = "Próxima trilha: " + secao.proximaTrilha.titulo;
        section.appendChild(proxima);
    }

    return section;
}

function renderConclusaoCurso(secao) {
    const section = document.createElement("section");
    section.className = "secao-conclusao-curso";

    const h2 = document.createElement("h2");
    h2.textContent = "\uD83C\uDFC6 " + secao.titulo;
    section.appendChild(h2);

    if (secao.texto) {
        const p = document.createElement("p");
        p.textContent = secao.texto;
        section.appendChild(p);
    }

    return section;
}

window.Renderers = window.Renderers || {};
window.Renderers.conclusao = renderConclusao;
window.Renderers["conclusao-trilha"] = renderConclusaoTrilha;
window.Renderers["conclusao-curso"] = renderConclusaoCurso;
