// niveis/js/home.js - desenha a home como um caminho de bolhas, uma bolha
// por Nível (Unidade), conectadas por linhas curvas (igual à Imagem 1).
// Clicar numa bolha desbloqueada navega pra nivel.html daquele nível.

const listaEl = document.getElementById("trilhas-lista");
let svgEl;

fetch("/api/levels")
    .then((res) => res.json())
    .then((niveis) => montarCaminho(niveis || []))
    .catch((err) => {
        console.error(err);
        listaEl.innerHTML = "<p class='carregando'>Não foi possível carregar os níveis.</p>";
    });

function montarCaminho(niveis) {
    listaEl.innerHTML = "";

    // O SVG das linhas fica "por baixo" das bolhas, ocupando todo o espaço
    // do container (position: absolute vem do CSS). Ele é criado uma vez,
    // e as linhas são redesenhadas depois que as bolhas já existirem na
    // tela (porque precisamos saber a posição real de cada uma).
    svgEl = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    svgEl.setAttribute("class", "linhas-niveis");
    listaEl.appendChild(svgEl);

    niveis
        .sort((a, b) => a.ordem - b.ordem)
        .forEach((nivel) => {
            const bolha = document.createElement("button");
            bolha.className = "nivel-bolha";
            bolha.textContent = "Nível " + nivel.ordem;

            if (nivel.bloqueado) {
                bolha.disabled = true;
                bolha.classList.add("bloqueada");
            } else {
                bolha.addEventListener("click", () => {
                    window.location.href = `niveis/nivel.html?levelId=${nivel.id}`;
                });
            }

            listaEl.appendChild(bolha);
        });

    // requestAnimationFrame espera o navegador terminar de desenhar as
    // bolhas na tela antes de medir a posição delas — se a gente medisse
    // antes disso, pegaria posição errada (ou zero).
    requestAnimationFrame(desenharLinhas);
}

function desenharLinhas() {
    if (!svgEl) return;
    svgEl.innerHTML = "";

    const bolhas = Array.from(listaEl.querySelectorAll(".nivel-bolha"));
    if (bolhas.length < 2) return;

    const containerRect = listaEl.getBoundingClientRect();
    const curva = 55;

    for (let i = 0; i < bolhas.length - 1; i++) {
        const r1 = bolhas[i].getBoundingClientRect();
        const r2 = bolhas[i + 1].getBoundingClientRect();

        const x1 = r1.left + r1.width / 2 - containerRect.left;
        const y1 = r1.top + r1.height / 2 - containerRect.top;
        const x2 = r2.left + r2.width / 2 - containerRect.left;
        const y2 = r2.top + r2.height / 2 - containerRect.top;

        const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
        const d = `M ${x1} ${y1} C ${x1} ${y1 + curva}, ${x2} ${y2 - curva}, ${x2} ${y2}`;
        path.setAttribute("d", d);
        path.setAttribute("fill", "none");
        path.setAttribute("stroke", "#8f7cff");
        path.setAttribute("stroke-width", "5");
        path.setAttribute("stroke-linecap", "round");
        svgEl.appendChild(path);
    }
}

window.addEventListener("resize", desenharLinhas);