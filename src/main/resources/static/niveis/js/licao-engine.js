// niveis/js/licao-engine.js
// Motor que busca os steps de uma lição pela API e desenha cada tela,
// de acordo com o "tipo" de cada step. JOGO tem um segundo nível de
// despacho pelo campo "subtipo".

const params = new URLSearchParams(window.location.search);
const lessonId = params.get("lessonId");
const levelId = params.get("levelId");
const urlNivel = levelId ? `nivel.html?levelId=${levelId}` : "/";
document.getElementById("fechar-licao").href = urlNivel;

const areaEl = document.getElementById("area-step");
const barraEl = document.getElementById("barra-progresso");

// Estado da lição inteira. Guardamos pontos ganhos/máximos em vez de só
// "acertos/erros", porque alguns jogos dão pontuação parcial (ex: acertar
// 3 de 5 itens numa classificação).
const estado = {
    stepAtual: 0,
    pontosGanhos: 0,
    pontosMaximos: 0,
    licao: null
};

if (!lessonId) {
    areaEl.innerHTML = "<p>Nenhuma lição informada na URL.</p>";
} else {
    fetch(`/api/lessons/${lessonId}/steps`)
        .then((res) => {
            // Sem essa checagem, um 404/500 virava um erro mudo e a tela ficava vazia.
            if (!res.ok) throw new Error("HTTP " + res.status);
            return res.json();
        })
        .then((steps) => {
            if (!Array.isArray(steps) || steps.length === 0) {
                areaEl.innerHTML = "<p>Esta lição ainda não tem conteúdo.</p>";
                return;
            }
            estado.licao = { steps };
            renderStep(steps[0]);
        })
        .catch((err) => {
            console.error(err);
            areaEl.innerHTML = "<p>Não foi possível carregar esta lição.</p>";
        });
}

// ============================================================
// DESPACHO PRINCIPAL — um "case" por tipo de step
// ============================================================
function renderStep(step) {
    areaEl.innerHTML = "";
    atualizarBarra();

    const conteudo = JSON.parse(typeof step.conteudoJson === "string" ? step.conteudoJson : JSON.stringify(step.conteudoJson));

    switch (step.tipo) {
        case "ABERTURA": renderAbertura(conteudo); break;
        case "CONTEUDO": renderConteudo(conteudo); break;
        case "COMPARACAO": renderComparacao(conteudo); break;
        case "VIDEO": renderVideo(conteudo); break;
        case "PARABENS": renderParabens(conteudo); break;
        case "QUIZ": renderQuiz(conteudo); break;
        case "JOGO": renderJogo(conteudo); break;
        case "RESUMO": renderResumo(conteudo); break;
        case "CONCLUSAO_TRILHA": renderConclusao(conteudo, "Continuar"); break;
        case "CONCLUSAO_CURSO": renderConclusao(conteudo, "Voltar para a home"); break;
        default: areaEl.innerHTML = `<p>Tipo de step desconhecido: ${step.tipo}</p>`;
    }
}

function proximoStep() {
    estado.stepAtual++;
    const steps = estado.licao.steps;
    if (estado.stepAtual < steps.length) {
        renderStep(steps[estado.stepAtual]);
    }
}

function atualizarBarra() {
    const total = estado.licao ? estado.licao.steps.length - 1 : 1;
    const pct = total > 0 ? (estado.stepAtual / total) * 100 : 0;
    barraEl.style.width = pct + "%";
}

// Soma pontos ganhos/máximos de qualquer step que dá XP (QUIZ ou JOGO).
function registrarPontuacao(ganhos, maximo) {
    estado.pontosGanhos += ganhos;
    estado.pontosMaximos += maximo;
}

function botaoContinuar(texto = "Continuar...", habilitado = true) {
    return `<button class="botao-continuar" id="btn-continuar" ${habilitado ? "" : "disabled"} onclick="proximoStep()">${texto}</button>`;
}

// ============================================================
// TIPOS "DE LEITURA" (sem pontuação)
// ============================================================
function renderAbertura(c) {
    areaEl.innerHTML = `
        <img class="mascote" src="img/mascote/${c.imagem}" alt="Mascote">
        <div class="card-licao centralizado">
            <div class="titulo-step">${c.titulo}</div>
            <p>${c.texto}</p>
        </div>
        <p class="tempo-xp">${c.tempo || ""} ${c.tempo ? "·" : ""} ${c.XP || ""}</p>
        <button class="botao-comecar" onclick="proximoStep()">Começar</button>
    `;
}

function renderConteudo(c) {
    areaEl.innerHTML = `
        <div class="card-licao">
            <div class="titulo-step">${c.titulo}</div>
            ${(c.paragrafos || []).map((p) => `<p class="paragrafo-conteudo">${p}</p>`).join("")}
            ${c.dica ? `
                <div class="box-dica">
                    <div class="titulo-dica">${c.titulo2 || "Você sabia?"}</div>
                    <p>${c.dica}</p>
                </div>` : ""}
        </div>
        ${c.imagem ? `<img class="mascote" src="img/mascote/${c.imagem}" alt="Mascote">` : ""}
        ${botaoContinuar()}
    `;
}

function renderComparacao(c) {
    areaEl.innerHTML = `
        <div class="card-licao">
            <div class="titulo-step">${c.titulo}</div>
            <div class="comparacao-colunas">
                <div class="comparacao-coluna">
                    <div class="titulo-coluna">${c.colunaA.titulo}</div>
                    <p>${c.colunaA.texto}</p>
                </div>
                <div class="comparacao-coluna">
                    <div class="titulo-coluna">${c.colunaB.titulo}</div>
                    <p>${c.colunaB.texto}</p>
                </div>
            </div>
        </div>
        ${c.imagem ? `<img class="mascote" src="img/mascote/${c.imagem}" alt="Mascote">` : ""}
        ${botaoContinuar()}
    `;
}

function renderVideo(c) {
    areaEl.innerHTML = `
        <div class="card-licao centralizado">
            <div class="titulo-step">${c.titulo}</div>
            <iframe class="video-embed" src="${paraEmbedYoutube(c.urlVideo)}" allowfullscreen></iframe>
        </div>
        ${botaoContinuar()}
    `;
}

// Converte um link comum do YouTube (watch?v= ou youtu.be/) no formato
// de embed que dá pra colocar num <iframe>.
function paraEmbedYoutube(url) {
    const match = url.match(/(?:v=|youtu\.be\/)([a-zA-Z0-9_-]+)/);
    const id = match ? match[1] : "";
    return `https://www.youtube.com/embed/${id}`;
}

function renderParabens(c) {
    areaEl.innerHTML = `
        <img class="mascote" src="img/mascote/${c.imagem}" alt="Mascote">
        <div class="card-licao centralizado">
            <div class="titulo-step">${c.titulo}</div>
            <p>${c.texto}</p>
        </div>
        <button class="botao-comecar" onclick="proximoStep()">Começar</button>
    `;
}

// ============================================================
// QUIZ (pergunta simples de múltipla escolha)
// ============================================================
function renderQuiz(c) {
    const opcoesHtml = c.opcoes
        .map((op, i) => `<button class="opcao-quiz" id="op-${i}" onclick="responderQuiz(${i})">${op}</button>`)
        .join("");

    areaEl.innerHTML = `
        ${c.imagem ? `<img class="mascote" src="img/mascote/${c.imagem}" alt="Mascote">` : ""}
        <div class="card-licao">
            <div class="titulo-step">Testando seus conhecimentos</div>
            <p>${c.pergunta}</p>
            ${opcoesHtml}
            <div id="explicacao-quiz"></div>
        </div>
        ${botaoContinuar("Continuar", false)}
    `;

    areaEl.dataset.correta = c.correta;
    areaEl.dataset.xp = c.xp;
    areaEl.dataset.explicacao = c.explicacao;
}

function responderQuiz(indiceEscolhido) {
    const correta = Number(areaEl.dataset.correta);
    const xp = Number(areaEl.dataset.xp);

    document.getElementById(`op-${correta}`).classList.add("correta");
    if (indiceEscolhido !== correta) {
        document.getElementById(`op-${indiceEscolhido}`).classList.add("errada");
    }

    document.querySelectorAll(".opcao-quiz").forEach((b) => (b.disabled = true));
    document.getElementById("explicacao-quiz").innerHTML =
        `<div class="explicacao-quiz">${areaEl.dataset.explicacao}</div>`;

    registrarPontuacao(indiceEscolhido === correta ? xp : 0, xp);
    document.getElementById("btn-continuar").disabled = false;
}

// ============================================================
// JOGO — despacho pelo "subtipo"
// ============================================================
function renderJogo(c) {
    switch (c.subtipo) {
        case "ordenar-sequencia": renderOrdenarSequencia(c); break;
        case "classificar-2-categorias":
        case "classificar-3-categorias": renderClassificar(c); break;
        case "cenario-multipla-escolha": renderCenarioMultiplaEscolha(c); break;
        case "cenario-escolha": renderCenarioEscolha(c); break;
        case "identificar-em-lista": renderIdentificarEmLista(c); break;
        default: areaEl.innerHTML = `<p>Subtipo de jogo desconhecido: ${c.subtipo}</p>`;
    }
}

// --- Subtipo: ordenar-sequencia ---
// O usuário clica nos itens embaralhados, na ordem que acha certa. Cada
// clique tira o item da lista de opções e bota na "sequência montada".
function renderOrdenarSequencia(c) {
    let minhaOrdem = [];
    const restantes = [...c.itensEmbaralhados];

    function redesenhar() {
        areaEl.innerHTML = `
            <div class="card-licao">
                <div class="instrucao-jogo">${c.instrucao}</div>
                <div class="sequencia-montada" id="sequencia-montada">
                    ${minhaOrdem.map((texto, i) => `<div class="sequencia-item">${i + 1}. ${texto}</div>`).join("")}
                </div>
                <div id="itens-restantes">
                    ${restantes.map((texto) => `<button class="item-jogo" onclick="ordenarClicar('${escapeAttr(texto)}')">${texto}</button>`).join("")}
                </div>
            </div>
            ${botaoContinuar("Confirmar ordem", restantes.length === 0)}
        `;
    }

    window.ordenarClicar = (texto) => {
        const idx = restantes.indexOf(texto);
        if (idx === -1) return;
        restantes.splice(idx, 1);
        minhaOrdem.push(texto);
        redesenhar();
    };

    // Sobrescreve o botão "Confirmar" pra checar a ordem em vez de só avançar.
    areaEl.addEventListener("click", function checar(e) {
        if (e.target.id !== "btn-continuar") return;
        areaEl.removeEventListener("click", checar);

        const acertouTudo = JSON.stringify(minhaOrdem) === JSON.stringify(c.ordemCorreta);
        registrarPontuacao(acertouTudo ? c.xp : 0, c.xp);

        areaEl.innerHTML += `
            <div class="explicacao-quiz">
                ${acertouTudo ? "Sequência correta!" : "Ordem correta: " + c.ordemCorreta.join(" → ")}
            </div>
            ${botaoContinuar()}
        `;
    });

    redesenhar();
}

function escapeAttr(texto) {
    return texto.replace(/'/g, "\\'");
}

// --- Subtipo: classificar-2-categorias / classificar-3-categorias ---
// Cada item mostra um botão por categoria; o usuário clica em qual
// categoria acha que aquele item pertence.
function renderClassificar(c) {
    const respostas = new Array(c.itens.length).fill(null);

    function redesenhar() {
        const linhas = c.itens.map((item, i) => `
            <div class="linha-classificar">
                <p>${item.texto}</p>
                <div class="categorias-botoes">
                    ${c.categorias.map((cat) => `
                        <button class="categoria-botao ${respostas[i] === cat ? "marcada" : ""}"
                                onclick="classificarClicar(${i}, '${escapeAttr(cat)}')">${cat}</button>
                    `).join("")}
                </div>
            </div>
        `).join("");

        const todasRespondidas = respostas.every((r) => r !== null);

        areaEl.innerHTML = `
            <div class="card-licao">
                <div class="instrucao-jogo">${c.instrucao}</div>
                ${linhas}
            </div>
            ${botaoContinuar("Confirmar", todasRespondidas)}
        `;
    }

    window.classificarClicar = (i, categoria) => {
        respostas[i] = categoria;
        redesenhar();
    };

    areaEl.addEventListener("click", function checar(e) {
        if (e.target.id !== "btn-continuar") return;
        areaEl.removeEventListener("click", checar);

        const acertos = c.itens.filter((item, i) => respostas[i] === item.categoria).length;
        const pontos = Math.round((acertos / c.itens.length) * c.xp);
        registrarPontuacao(pontos, c.xp);

        areaEl.innerHTML += `
            <div class="explicacao-quiz">Você acertou ${acertos} de ${c.itens.length} (${pontos} XP).</div>
            ${botaoContinuar()}
        `;
    });

    redesenhar();
}

// --- Subtipo: cenario-multipla-escolha ---
// Várias "rodadas", cada uma como um mini-quiz. Ao final, pode ter uma
// pergunta bônus (não conta pontos, é só reflexão).
function renderCenarioMultiplaEscolha(c) {
    let rodadaAtual = 0;
    let acertos = 0;

    function redesenharRodada() {
        const r = c.rodadas[rodadaAtual];
        areaEl.innerHTML = `
            <div class="card-licao">
                <div class="instrucao-jogo">${c.instrucao}</div>
                <div class="rodada-contador">Situação ${rodadaAtual + 1} de ${c.rodadas.length}</div>
                <p>${r.situacao}</p>
                ${r.opcoes.map((op, i) => `<button class="item-jogo" id="cme-op-${i}" onclick="cmeResponder(${i})">${op}</button>`).join("")}
            </div>
            ${botaoContinuar(rodadaAtual < c.rodadas.length - 1 ? "Próxima situação" : "Continuar", false)}
        `;
    }

    window.cmeResponder = (i) => {
        const r = c.rodadas[rodadaAtual];
        const indiceCorreto = r.opcoes.indexOf(r.respostaCorreta);
        document.getElementById(`cme-op-${indiceCorreto}`).classList.add("correta");
        if (i !== indiceCorreto) document.getElementById(`cme-op-${i}`).classList.add("errada");
        else acertos++;

        document.querySelectorAll(".item-jogo").forEach((b) => (b.disabled = true));
        document.getElementById("btn-continuar").disabled = false;
    };

    areaEl.addEventListener("click", function avancar(e) {
        if (e.target.id !== "btn-continuar") return;

        if (rodadaAtual < c.rodadas.length - 1) {
            rodadaAtual++;
            redesenharRodada();
            return;
        }

        // Acabaram as rodadas pontuadas — se tiver bônus, mostra antes do resumo.
        areaEl.removeEventListener("click", avancar);
        const pontos = Math.round((acertos / c.rodadas.length) * c.xp);
        registrarPontuacao(pontos, c.xp);

        if (c.perguntaBonus) {
            renderPerguntaBonus(c.perguntaBonus, pontos, c.rodadas.length, acertos);
        } else {
            areaEl.innerHTML = `<div class="explicacao-quiz">Você acertou ${acertos} de ${c.rodadas.length} (${pontos} XP).</div>${botaoContinuar()}`;
        }
    });

    redesenharRodada();
}

function renderPerguntaBonus(bonus, pontos, totalRodadas, acertos) {
    areaEl.innerHTML = `
        <div class="card-licao">
            <div class="instrucao-jogo">Pergunta bônus (não vale XP)</div>
            <p>${bonus.pergunta}</p>
            ${bonus.opcoes.map((op, i) => `<button class="item-jogo" id="bonus-op-${i}" onclick="bonusResponder(${i})">${op}</button>`).join("")}
        </div>
        ${botaoContinuar("Continuar", false)}
    `;

    window.bonusResponder = (i) => {
        const indiceCorreto = bonus.opcoes.indexOf(bonus.respostaCorreta);
        document.getElementById(`bonus-op-${indiceCorreto}`).classList.add("correta");
        if (i !== indiceCorreto) document.getElementById(`bonus-op-${i}`).classList.add("errada");
        document.querySelectorAll(".item-jogo").forEach((b) => (b.disabled = true));
        document.getElementById("btn-continuar").disabled = false;
    };
}

// --- Subtipo: cenario-escolha (duas opções por rodada) ---
function renderCenarioEscolha(c) {
    let rodadaAtual = 0;
    let acertos = 0;

    function redesenharRodada() {
        const r = c.rodadas[rodadaAtual];
        areaEl.innerHTML = `
            <div class="card-licao">
                <div class="instrucao-jogo">${c.instrucao}</div>
                <div class="rodada-contador">Situação ${rodadaAtual + 1} de ${c.rodadas.length}</div>
                <p>${r.situacao}</p>
                <div class="opcoes-duas">
                    <button class="item-jogo" id="ce-op-A" onclick="ceResponder('opcaoA')">${r.opcaoA}</button>
                    <button class="item-jogo" id="ce-op-B" onclick="ceResponder('opcaoB')">${r.opcaoB}</button>
                </div>
                <div id="ce-explicacao"></div>
            </div>
            ${botaoContinuar(rodadaAtual < c.rodadas.length - 1 ? "Próxima situação" : "Continuar", false)}
        `;
    }

    window.ceResponder = (escolha) => {
        const r = c.rodadas[rodadaAtual];
        const idCorreto = r.respostaCorreta === "opcaoA" ? "ce-op-A" : "ce-op-B";
        document.getElementById(idCorreto).classList.add("correta");
        if (escolha !== r.respostaCorreta) {
            document.getElementById(escolha === "opcaoA" ? "ce-op-A" : "ce-op-B").classList.add("errada");
        } else {
            acertos++;
        }
        document.getElementById("ce-op-A").disabled = true;
        document.getElementById("ce-op-B").disabled = true;
        document.getElementById("ce-explicacao").innerHTML = `<div class="explicacao-quiz">${r.explicacao}</div>`;
        document.getElementById("btn-continuar").disabled = false;
    };

    areaEl.addEventListener("click", function avancar(e) {
        if (e.target.id !== "btn-continuar") return;

        if (rodadaAtual < c.rodadas.length - 1) {
            rodadaAtual++;
            redesenharRodada();
            return;
        }

        areaEl.removeEventListener("click", avancar);
        const pontos = Math.round((acertos / c.rodadas.length) * c.xp);
        registrarPontuacao(pontos, c.xp);
        areaEl.innerHTML = `<div class="explicacao-quiz">Você acertou ${acertos} de ${c.rodadas.length} (${pontos} XP).</div>${botaoContinuar()}`;
    });

    redesenharRodada();
}

// --- Subtipo: identificar-em-lista ---
// O usuário clica em QUAL item da lista acha suspeito. Só um item tem
// suspeito: true no JSON.
function renderIdentificarEmLista(c) {
    areaEl.innerHTML = `
        <div class="card-licao">
            <div class="instrucao-jogo">${c.instrucao}</div>
            ${c.itens.map((item, i) => `<button class="item-jogo" id="lista-item-${i}" onclick="listaClicar(${i})">${item.texto}</button>`).join("")}
            <div id="lista-explicacao"></div>
        </div>
        ${botaoContinuar("Continuar", false)}
    `;

    window.listaClicar = (i) => {
        const indiceCorreto = c.itens.findIndex((item) => item.suspeito);
        document.getElementById(`lista-item-${indiceCorreto}`).classList.add("correta");
        const acertou = i === indiceCorreto;
        if (!acertou) document.getElementById(`lista-item-${i}`).classList.add("errada");

        document.querySelectorAll(".item-jogo").forEach((b) => (b.disabled = true));
        if (c.itens[indiceCorreto].motivo) {
            document.getElementById("lista-explicacao").innerHTML =
                `<div class="explicacao-quiz">${c.itens[indiceCorreto].motivo}</div>`;
        }

        registrarPontuacao(acertou ? c.xp : 0, c.xp);
        document.getElementById("btn-continuar").disabled = false;
    };
}

// ============================================================
// RESUMO / CONCLUSÕES
// ============================================================
function renderResumo(c) {
    const aproveitamento = estado.pontosMaximos > 0
        ? Math.round((estado.pontosGanhos / estado.pontosMaximos) * 100)
        : 100;

    areaEl.innerHTML = `
        ${c.imagem ? `<img class="mascote" src="img/mascote/${c.imagem}" alt="Mascote">` : ""}
        <div class="card-licao centralizado">
            <div class="titulo-step">${c.titulo}</div>
            <p class="xp-total">${c.texto}${estado.pontosGanhos} XP</p>
            <p>Aproveitamento: ${aproveitamento}%</p>
        </div>
        <button class="botao-continuar" onclick="finalizarLicao()">Voltar para o nível</button>
    `;
}

function renderConclusao(c, textoBotao) {
    areaEl.innerHTML = `
        <div class="card-licao centralizado">
            <div class="titulo-step">${c.titulo}</div>
            <p>${c.texto}</p>
            ${c.badge ? `<div class="badge-conquista">🏅 ${c.badge}</div>` : ""}
        </div>
        <button class="botao-continuar" onclick="finalizarLicao()">${textoBotao}</button>
    `;
}

// Envia o resultado da lição pro backend e volta pro mapa do nível.
// Usa o usuário logado (salvo no localStorage no login/cadastro); se não
// houver, cai no 1 como antes.
function finalizarLicao() {
    const usuarioId = localStorage.getItem("userId") || 1;

    fetch(`/api/progress?usuarioId=${usuarioId}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            lessonId: Number(lessonId),
            xpGanho: estado.pontosGanhos,
            totalAcertos: estado.pontosGanhos,
            totalPerguntas: estado.pontosMaximos
        })
    })
    .catch((err) => console.error("Falha ao salvar progresso:", err))
    .finally(() => { window.location.href = urlNivel; });
}