//redireciona o nível certo e desenha o caminho atraz pro front
const params = new URLSearchParams(window.location.search);
const levelId = params.get("levelId");

const listaEl = document.getElementById("licoes-lista");
let svgEl;

if (!levelId) {
    document.getElementById("nivel-descricao").textContent =
        "Nenhum nível informado na URL (esperado: nivel.html?levelId=1).";
} else {
    fetch(`/api/levels/${levelId}`)
        .then((res) => res.json())
        .then((nivel) => renderNivel(nivel))
        .catch((err) => {
            console.error(err);
            document.getElementById("nivel-descricao").textContent =
                "Não foi possível carregar este nível.";
        });
}

function renderNivel(nivel) {
    document.getElementById("nivel-titulo").textContent = "NÍVEL " + nivel.ordem;
    document.getElementById("nivel-descricao").textContent = nivel.descricao;

    listaEl.innerHTML = "";

    svgEl = document.createElementNS("http://www.w3.org/2000/svg", "svg");
    svgEl.setAttribute("class", "linhas-licoes");
    listaEl.appendChild(svgEl);

    const licoes = (nivel.licoes || []).sort((a, b) => a.ordem - b.ordem);

    licoes.forEach((licao, indice) => {
        const pill = document.createElement("button");
        pill.className = "licao-pill";
        pill.textContent = `${indice + 1}. ${licao.titulo}`;

        if (licao.bloqueada) {
            pill.disabled = true;
            pill.classList.add("bloqueada");
        } else {
            pill.addEventListener("click", () => {
                window.location.href = `licao.html?lessonId=${licao.id}&levelId=${levelId}`;
            });
        }

        listaEl.appendChild(pill);
    });

    // O botão de Desafio Final só destrava quando TODAS as lições desse nível estiverem concluídas. Por enquanto, como ainda não temos o
    // progresso do usuário ligado ele fica sempre travado, tem que trocar essa condição depois.
    const todasDesbloqueadas = licoes.every((l) => !l.bloqueada);
    const btnDesafio = document.getElementById("btn-desafio-final");
    if (todasDesbloqueadas && licoes.length > 0) {
        // btnDesafio.disabled = false;
    }

    requestAnimationFrame(desenharLinhas);
}

function desenharLinhas() {
    if (!svgEl) return;
    svgEl.innerHTML = "";

    const pills = Array.from(listaEl.querySelectorAll(".licao-pill"));
    if (pills.length < 2) return;

    const containerRect = listaEl.getBoundingClientRect();
    const curva = 45;

    for (let i = 0; i < pills.length - 1; i++) {
        const r1 = pills[i].getBoundingClientRect();
        const r2 = pills[i + 1].getBoundingClientRect();

        const x1 = r1.left + r1.width / 2 - containerRect.left;
        const y1 = r1.top + r1.height / 2 - containerRect.top;
        const x2 = r2.left + r2.width / 2 - containerRect.left;
        const y2 = r2.top + r2.height / 2 - containerRect.top;

        const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
        const d = `M ${x1} ${y1} C ${x1} ${y1 + curva}, ${x2} ${y2 - curva}, ${x2} ${y2}`;
        path.setAttribute("d", d);
        path.setAttribute("fill", "none");
        path.setAttribute("stroke", "#8f7cff");
        path.setAttribute("stroke-width", "4");
        path.setAttribute("stroke-linecap", "round");
        svgEl.appendChild(path);
    }
}

window.addEventListener("resize", desenharLinhas);