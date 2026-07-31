// app.js - ponto de entrada da tela de missão.
// Uso: trilhas/missao.html?missao=1-1
(function () {
    const params = new URLSearchParams(window.location.search);
    const missaoId = params.get("missao");
    const container = document.getElementById("conteudo");

    if (!missaoId) {
        container.textContent = "Missão não especificada. Use ?missao=1-1 na URL.";
        return;
    }

    fetch("trilhas-index.json")
        .then((res) => res.json())
        .then((indice) => {
            let arquivo = null;
            for (const trilha of indice.trilhas) {
                const encontrada = (trilha.missoes || []).find((m) => m.id === missaoId);
                if (encontrada) {
                    arquivo = encontrada.arquivo;
                    break;
                }
            }
            if (!arquivo) {
                throw new Error("Missão não encontrada no índice: " + missaoId);
            }
            return fetch(arquivo);
        })
        .then((res) => res.json())
        .then((dados) => montarMissao(dados))
        .catch((err) => {
            console.error(err);
            container.textContent = "Erro ao carregar a missão. Verifique o console para detalhes.";
        });

    function montarMissao(dados) {
        container.innerHTML = "";

        const cabecalho = document.createElement("header");
        cabecalho.className = "missao-cabecalho";

        const h1 = document.createElement("h1");
        h1.textContent = dados.titulo;
        cabecalho.appendChild(h1);

        const meta = document.createElement("p");
        meta.className = "missao-meta";
        const partes = [];
        if (dados.duracaoEstimada) partes.push(dados.duracaoEstimada);
        if (dados.xp) partes.push("+" + dados.xp + " XP");
        meta.textContent = partes.join(" · ");
        cabecalho.appendChild(meta);

        container.appendChild(cabecalho);

        (dados.secoes || []).forEach((secao) => {
            const renderer = window.Renderers[secao.tipo];
            if (renderer) {
                container.appendChild(renderer(secao));
            } else {
                console.warn("Sem renderer implementado para o tipo:", secao.tipo);
            }
        });
    }
})();
