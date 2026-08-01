// script.js - monta a lista de trilhas e missões dinamicamente a partir de
// trilhas/trilhas-index.json, em vez de botões fixos no HTML.
// Também recria o efeito visual das linhas curvas conectando os botões,
// agora desenhadas dentro de cada card de trilha (uma "trilha" por vez).

const listaEl = document.getElementById("trilhas-lista");
const gridsParaRedesenhar = [];

fetch("trilhas/trilhas-index.json")
    .then((res) => res.json())
    .then((dados) => montarTrilhas(dados.trilhas || []))
    .catch((err) => {
        console.error(err);
        listaEl.innerHTML = "<p class='carregando'>Não foi possível carregar as trilhas.</p>";
    });

function montarTrilhas(trilhas) {
    listaEl.innerHTML = "";
    gridsParaRedesenhar.length = 0;

    trilhas.forEach((trilha) => {

        // Container da fase
        const fase = document.createElement("div");
        fase.className = "fase";

        // Botão da fase
        const btnFase = document.createElement("button");
        btnFase.className = "fase-btn";
        btnFase.textContent = "Nível " + trilha.numero;

        if (trilha.bloqueada) {
            btnFase.disabled = true;
            btnFase.classList.add("bloqueada");
        }

        fase.appendChild(btnFase);

        // Card da trilha (fica escondido)
        const card = document.createElement("section");
        card.className = "trilha-card";
        card.style.display = "none";

        const titulo = document.createElement("h4");
        titulo.className = "trilha-titulo";
        titulo.textContent = trilha.titulo;

        card.appendChild(titulo);

        if (trilha.descricao) {
            const descricao = document.createElement("p");
            descricao.className = "trilha-descricao";
            descricao.textContent = trilha.descricao;
            card.appendChild(descricao);
        }

        const missoesGrid = document.createElement("div");
        missoesGrid.className = "missoes-grid";

        const svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
        svg.setAttribute("class", "linhas-trilha");
        missoesGrid.appendChild(svg);

        (trilha.missoes || []).forEach((missao, indice) => {

            const btn = document.createElement("button");
            btn.className = "missao-pill";
            btn.textContent = (indice + 1) + ". " + missao.titulo;

            if (trilha.bloqueada) {
                btn.disabled = true;
                btn.classList.add("bloqueada");
            } else {
                btn.addEventListener("click", () => {
                    window.location.href =
                        `trilhas/missao.html?missao=${missao.id}`;
                });
            }

            missoesGrid.appendChild(btn);

        });

        card.appendChild(missoesGrid);
        fase.appendChild(card);
        listaEl.appendChild(fase);

        gridsParaRedesenhar.push({
            grid: missoesGrid,
            svg
        });

        // Abrir/Fechar a fase
        btnFase.addEventListener("click", () => {

            // Fecha todas as outras fases
            document.querySelectorAll(".trilha-card").forEach((c) => {
                if (c !== card) c.style.display = "none";
            });

            // Alterna a fase clicada
            if (card.style.display === "none") {
                card.style.display = "block";

                requestAnimationFrame(() => {
                    desenharLinhasTrilha(missoesGrid, svg);
                });

            } else {
                card.style.display = "none";
            }

        });

    });
}

function desenharLinhasTrilha(grid, svg) {
    svg.innerHTML = "";

    const botoes = Array.from(grid.querySelectorAll(".missao-pill"));
    if (botoes.length < 2) return;

    const gridRect = grid.getBoundingClientRect();
    const curva = 55;

    for (let i = 0; i < botoes.length - 1; i++) {
        const r1 = botoes[i].getBoundingClientRect();
        const r2 = botoes[i + 1].getBoundingClientRect();

        const x1 = r1.left + r1.width / 2 - gridRect.left;
        const y1 = r1.top + r1.height / 2 - gridRect.top;
        const x2 = r2.left + r2.width / 2 - gridRect.left;
        const y2 = r2.top + r2.height / 2 - gridRect.top;

        const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
        const d = `
            M ${x1} ${y1}
            C ${x1} ${y1 + curva},
              ${x2} ${y2 - curva},
              ${x2} ${y2}
        `;
        path.setAttribute("d", d);
        path.setAttribute("fill", "none");
        path.setAttribute("stroke", "#8f7cff");
        path.setAttribute("stroke-width", "5");
        path.setAttribute("stroke-linecap", "round");
        svg.appendChild(path);
    }
}

window.addEventListener("resize", () => {
    gridsParaRedesenhar.forEach(({ grid, svg }) => desenharLinhasTrilha(grid, svg));
});
