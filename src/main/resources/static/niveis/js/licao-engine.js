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
    atualizarBotaoVoltar();

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
        case "CONCLUSAO_TRILHA": renderConclusao(conteudo, "Voltar para o nível"); break;
        case "CONCLUSAO_CURSO": renderConclusao(conteudo, "Voltar", "/"); break;
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

function botaoContinuar(texto = "Continuar...", habilitado = true, acao = "proximoStep()") {
    return `<button class="botao-continuar" id="btn-continuar" ${habilitado ? "" : "disabled"} onclick="${acao}">${texto}</button>`;
}


// TIPOS "DE LEITURA" (sem pontuação)

function renderAbertura(c) {
    areaEl.innerHTML = `
        <div class="titulo-step titulo-largo">${c.titulo}</div>
        <img class="mascote mascote-grande" src="img/${c.imagem}" alt="Mascote">
        <p class="texto-solto">${c.texto}</p>
        <p class="tempo-xp">${c.tempo || ""} ${c.tempo ? "·" : ""} ${c.XP || ""}</p>
        <button class="botao-comecar" onclick="proximoStep()">Começar</button>
    `;
}

function renderConteudo(c) {
    areaEl.innerHTML = `
        <div class="linha-topo">
            <div class="titulo-step">${c.titulo}</div>
            ${c.imagem ? `<img class="mascote mascote-pequeno" src="img/${c.imagem}" alt="Mascote">` : ""}
        </div>
        ${(c.paragrafos || []).map((p) => `<p class="bloco-texto">${p}</p>`).join("")}
        ${c.dica ? `
            <div class="titulo-step">${c.titulo2 || "Você sabia?"}</div>
            <div class="box-dica-solto">${c.dica}</div>` : ""}
        ${botaoContinuar()}
    `;
}

function renderComparacao(c) {
    // Agora desenha todas as colunas que existirem (A, B, C, D).
    // Antes só A e B apareciam, e várias lições do data.sql têm C e D.
    const colunas = ["colunaA", "colunaB", "colunaC", "colunaD"].filter((k) => c[k]);
    areaEl.innerHTML = `
        <div class="linha-topo">
            <div class="titulo-step">${c.titulo}</div>
            ${c.imagem ? `<img class="mascote mascote-pequeno" src="img/${c.imagem}" alt="Mascote">` : ""}
        </div>
        ${colunas.map((k) => `
            <div class="comparacao-item">
                <div class="comparacao-titulo">${c[k].titulo}</div>
                <p class="bloco-texto">${c[k].texto}</p>
            </div>`).join("")}
        ${botaoContinuar()}
    `;
}
function renderVideo(c) {
    areaEl.innerHTML = `
        <div class="linha-topo">
            <div class="titulo-step">${c.titulo}</div>
            ${c.imagem ? `<img class="mascote mascote-pequeno" src="img/${c.imagem}" alt="Mascote">` : ""}
        </div>
        <iframe class="video-embed" src="${paraEmbedYoutube(c.urlVideo)}" allowfullscreen></iframe>
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
        <div class="titulo-step titulo-largo">${c.titulo}</div>
        <img class="mascote mascote-grande" src="img/${c.imagem}" alt="Mascote">
        <p class="texto-solto">${c.texto}</p>
        <button class="botao-comecar" onclick="proximoStep()">Começar</button>
    `;
}

// ============================================================
// QUIZ (pergunta simples de múltipla escolha)
// ============================================================
const MASCOTES_ACERTO = [
    "byte-acertou-pergunta-2.svg",
    "byte-acertou-pergunta-1.svg"
];
const MASCOTES_ERRO = [
    "byte-errou-pergunta-1.svg",
    "byte-errou-pergunta-2.svg",
    "byte-errou-pergunta-3.svg"
];

function sortear(lista) {
    return lista[Math.floor(Math.random() * lista.length)];
}

function renderQuiz(c) {
    const opcoesHtml = c.opcoes
        .map((op, i) => `<button class="opcao-quiz" id="op-${i}" onclick="responderQuiz(${i})">${op}</button>`)
        .join("");

    areaEl.innerHTML = `
        <div class="titulo-step">Testando seus conhecimentos</div>
        <div class="quiz-topo">
            ${c.imagem ? `<img class="mascote mascote-medio" src="img/${c.imagem}" alt="Mascote">` : ""}
            <div class="balao">${c.pergunta}</div>
        </div>
        <div class="quiz-opcoes">${opcoesHtml}</div>
        <button class="botao-continuar" id="btn-continuar" disabled onclick="mostrarResultadoQuiz()">Confirmar</button>
    `;

    areaEl.dataset.correta = c.correta;
    areaEl.dataset.xp = c.xp;
    areaEl.dataset.explicacao = c.explicacao;
}

function responderQuiz(indiceEscolhido) {
    const correta = Number(areaEl.dataset.correta);
    const xp = Number(areaEl.dataset.xp);
    const acertou = indiceEscolhido === correta;

    document.getElementById(`op-${correta}`).classList.add("correta");
    if (!acertou) {
        document.getElementById(`op-${indiceEscolhido}`).classList.add("errada");
    }
    document.querySelectorAll(".opcao-quiz").forEach((b) => (b.disabled = true));

    areaEl.dataset.acertou = acertou ? "1" : "0";
    registrarPontuacao(acertou ? xp : 0, xp);
    document.getElementById("btn-continuar").disabled = false;
}

// Tela que aparece depois de responder: feliz se acertou, triste se errou.
function mostrarResultadoQuiz() {
    const acertou = areaEl.dataset.acertou === "1";
    const xp = Number(areaEl.dataset.xp);
    const explicacao = areaEl.dataset.explicacao;

    areaEl.innerHTML = `
        <div class="titulo-step titulo-largo">
            ${acertou ? `Parabéns +${xp} XP` : "Passou perto.. mas infelizmente não foi dessa vez!"}
        </div>
        <img class="mascote mascote-grande"
             src="img/${sortear(acertou ? MASCOTES_ACERTO : MASCOTES_ERRO)}" alt="Mascote">
        <p class="texto-solto">${explicacao}</p>
        ${botaoContinuar("Continuar")}
    `;
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
        case "simulacao-email": renderSimulacaoEmail(c); break;
        case "identificar-em-lista": renderIdentificarEmLista(c); break;
        default: areaEl.innerHTML = `<p>Subtipo de jogo desconhecido: ${c.subtipo}</p>`;
    }
}

// --- Subtipo: ordenar-sequencia ---
// O usuário clica nos itens embaralhados, na ordem que acha certa. Cada
// clique tira o item da lista de opções e bota na "sequência montada".
function renderOrdenarSequencia(c) {
    const minhaOrdem = [];
    const restantes = [...c.itensEmbaralhados];

    function redesenhar() {
        areaEl.innerHTML = `
            <div class="card-licao">
                <div class="instrucao-jogo">${c.instrucao}</div>
                <div class="sequencia-montada">
                    ${minhaOrdem.map((t, i) => `<div class="sequencia-item">${i + 1}. ${t}</div>`).join("")}
                </div>
                ${restantes.map((t, i) => `<button class="item-jogo" onclick="ordenarClicar(${i})">${t}</button>`).join("")}
            </div>
            ${botaoContinuar("Confirmar ordem", restantes.length === 0, "ordenarConfirmar()")}
        `;
    }

    window.ordenarClicar = (i) => {
        minhaOrdem.push(restantes.splice(i, 1)[0]);
        redesenhar();
    };

    window.ordenarConfirmar = () => {
        const acertou = JSON.stringify(minhaOrdem) === JSON.stringify(c.ordemCorreta);
        registrarPontuacao(acertou ? c.xp : 0, c.xp);
        areaEl.innerHTML = `
            <div class="card-licao">
                <div class="explicacao-quiz">
                    ${acertou ? "Sequência correta!" : "Ordem correta: " + c.ordemCorreta.join(" → ")}
                </div>
            </div>
            ${botaoContinuar()}
        `;
    };

    redesenhar();
}


function escapeAttr(texto) {
    return texto.replace(/'/g, "\\'");
}

// --- Subtipo: classificar-2-categorias / classificar-3-categorias ---
// Cada item mostra um botão por categoria; o usuário clica em qual
// categoria acha que aquele item pertence.

// --- classificar-2-categorias / classificar-3-categorias ---
function renderClassificar(c) {
    const respostas = new Array(c.itens.length).fill(null);

    function redesenhar() {
        const linhas = c.itens.map((item, i) => `
            <div class="linha-classificar">
                <p>${item.texto}</p>
                <div class="categorias-botoes">
                    ${c.categorias.map((cat, j) => `
                        <button class="categoria-botao ${respostas[i] === cat ? "marcada" : ""}"
                                onclick="classificarClicar(${i}, ${j})">${cat}</button>
                    `).join("")}
                </div>
            </div>
        `).join("");

        areaEl.innerHTML = `
            <div class="card-licao">
                <div class="instrucao-jogo">${c.instrucao}</div>
                ${linhas}
            </div>
            ${botaoContinuar("Confirmar", respostas.every((r) => r !== null), "classificarConfirmar()")}
        `;
    }

    window.classificarClicar = (i, j) => {
        respostas[i] = c.categorias[j];
        redesenhar();
    };

    window.classificarConfirmar = () => {
        const acertos = c.itens.filter((item, i) => respostas[i] === item.categoria).length;
        const pontos = Math.round((acertos / c.itens.length) * c.xp);
        registrarPontuacao(pontos, c.xp);

        const detalhes = c.itens.map((item, i) => {
            const ok = respostas[i] === item.categoria;
            return `<div class="explicacao-quiz">${ok ? "✅" : "❌"} ${item.texto}
                ${ok ? "" : `<br><small>Resposta certa: <strong>${item.categoria}</strong></small>`}</div>`;
        }).join("");

        areaEl.innerHTML = `
            <div class="card-licao">
                <div class="instrucao-jogo">Você acertou ${acertos} de ${c.itens.length} (${pontos} XP)</div>
                ${detalhes}
            </div>
            ${botaoContinuar()}
        `;
    };

    redesenhar();
}


function renderCenarioMultiplaEscolha(c) {
    let rodadaAtual = 0;
    let acertos = 0;

    function redesenharRodada() {
        const r = c.rodadas[rodadaAtual];
        const ultima = rodadaAtual === c.rodadas.length - 1;
        areaEl.innerHTML = `
            <div class="card-licao">
                <div class="instrucao-jogo">${c.instrucao}</div>
                <div class="rodada-contador">Situação ${rodadaAtual + 1} de ${c.rodadas.length}</div>
                <p>${r.situacao}</p>
                ${r.opcoes.map((op, i) => `<button class="item-jogo" id="cme-op-${i}" onclick="cmeResponder(${i})">${op}</button>`).join("")}
            </div>
            ${botaoContinuar(ultima ? "Continuar" : "Próxima situação", false, "cmeAvancar()")}
        `;
    }

    window.cmeResponder = (i) => {
        const r = c.rodadas[rodadaAtual];
        const indiceCorreto = r.opcoes.indexOf(r.respostaCorreta);
        document.getElementById(`cme-op-${indiceCorreto}`).classList.add("correta");
        if (i === indiceCorreto) acertos++;
        else document.getElementById(`cme-op-${i}`).classList.add("errada");
        document.querySelectorAll(".item-jogo").forEach((b) => (b.disabled = true));
        document.getElementById("btn-continuar").disabled = false;
    };

    window.cmeAvancar = () => {
        if (rodadaAtual < c.rodadas.length - 1) {
            rodadaAtual++;
            redesenharRodada();
            return;
        }
        const pontos = Math.round((acertos / c.rodadas.length) * c.xp);
        registrarPontuacao(pontos, c.xp);
        const resultado = `Você acertou ${acertos} de ${c.rodadas.length} (${pontos} XP).`;

        if (c.perguntaBonus) renderPerguntaBonus(c.perguntaBonus, resultado);
        else areaEl.innerHTML = `<div class="card-licao"><div class="explicacao-quiz">${resultado}</div></div>${botaoContinuar()}`;
    };

    redesenharRodada();
}

function renderPerguntaBonus(bonus, resultado) {
    areaEl.innerHTML = `
        <div class="card-licao">
            <div class="explicacao-quiz">${resultado}</div>
            <div class="instrucao-jogo" style="margin-top:14px">Pergunta bônus (não vale XP)</div>
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
        const ultima = rodadaAtual === c.rodadas.length - 1;
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
            ${botaoContinuar(ultima ? "Continuar" : "Próxima situação", false, "ceAvancar()")}
        `;
    }

    window.ceResponder = (escolha) => {
        const r = c.rodadas[rodadaAtual];
        const idCorreto = r.respostaCorreta === "opcaoA" ? "ce-op-A" : "ce-op-B";
        document.getElementById(idCorreto).classList.add("correta");
        if (escolha === r.respostaCorreta) acertos++;
        else document.getElementById(escolha === "opcaoA" ? "ce-op-A" : "ce-op-B").classList.add("errada");
        document.getElementById("ce-op-A").disabled = true;
        document.getElementById("ce-op-B").disabled = true;
        document.getElementById("ce-explicacao").innerHTML = `<div class="explicacao-quiz">${r.explicacao}</div>`;
        document.getElementById("btn-continuar").disabled = false;
    };

    window.ceAvancar = () => {
        if (rodadaAtual < c.rodadas.length - 1) {
            rodadaAtual++;
            redesenharRodada();
            return;
        }
        const pontos = Math.round((acertos / c.rodadas.length) * c.xp);
        registrarPontuacao(pontos, c.xp);
        areaEl.innerHTML = `
            <div class="card-licao"><div class="explicacao-quiz">Você acertou ${acertos} de ${c.rodadas.length} (${pontos} XP).</div></div>
            ${botaoContinuar()}
        `;
    };

    redesenharRodada();
}
// --- simulacao-email ---
function renderSimulacaoEmail(c) {
    let atual = 0;
    let acertos = 0;
    let respondeu = false;

    function redesenhar() {
        const e = c.cenarios[atual];
        const ultimo = atual === c.cenarios.length - 1;
        respondeu = false;
        areaEl.innerHTML = `
            <div class="card-licao">
                <div class="instrucao-jogo">${c.instrucao}</div>
                ${c.cenarios.length > 1 ? `<div class="rodada-contador">E-mail ${atual + 1} de ${c.cenarios.length}</div>` : ""}
                <div class="email-simulado">
                    <div class="email-linha"><strong>De:</strong> ${e.remetente}</div>
                    <div class="email-linha"><strong>Assunto:</strong> ${e.assunto}</div>
                    <p class="email-corpo">${e.corpo}</p>
                </div>
                <div class="opcoes-duas" id="email-botoes">
                    <button class="item-jogo" id="email-manter" onclick="emailResponder(false)">Manter</button>
                    <button class="item-jogo" id="email-deletar" onclick="emailResponder(true)">Deletar</button>
                </div>
                <div id="email-feedback"></div>
            </div>
            ${botaoContinuar(ultimo ? "Continuar" : "Próximo e-mail", false, "emailAvancar()")}
        `;
    }

    window.emailResponder = (deletar) => {
        if (respondeu) return;
        respondeu = true;
        const e = c.cenarios[atual];
        const acertou = deletar === e.ehFraude;
        if (acertou) acertos++;

        document.getElementById(e.ehFraude ? "email-deletar" : "email-manter").classList.add("correta");
        if (!acertou) document.getElementById(deletar ? "email-deletar" : "email-manter").classList.add("errada");
        document.querySelectorAll("#email-botoes button").forEach((b) => (b.disabled = true));

        const sinais = (e.sinaisPresentes || []).length
            ? `<br><small>Sinais: ${e.sinaisPresentes.join(", ")}</small>` : "";
        document.getElementById("email-feedback").innerHTML =
            `<div class="explicacao-quiz">${acertou ? e.feedbackCorreto : e.feedbackIncorreto}${sinais}</div>`;
        document.getElementById("btn-continuar").disabled = false;
    };

    window.emailAvancar = () => {
        if (atual < c.cenarios.length - 1) {
            atual++;
            redesenhar();
            return;
        }
        const xpMaximo = c.cenarios.reduce((soma, e) => soma + (e.xp || 0), 0);
        const pontos = Math.round((acertos / c.cenarios.length) * xpMaximo);
        registrarPontuacao(pontos, xpMaximo);
        areaEl.innerHTML = `
            <div class="card-licao"><div class="explicacao-quiz">Você acertou ${acertos} de ${c.cenarios.length} (${pontos} XP).</div></div>
            ${botaoContinuar()}
        `;
    };

    redesenhar();
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
function temProximoStep() {
    return estado.stepAtual < estado.licao.steps.length - 1;
}

// Se ainda há step depois (ex: conclusão do nível), avança; senão sai da lição.
function avancarOuFinalizar(destino) {
    if (temProximoStep()) proximoStep();
    else finalizarLicao(destino);
}

// Grava o resultado uma única vez, mesmo se a pessoa voltar e reabrir o resumo.
function salvarProgresso() {
    if (estado.salvando) return;
    const usuarioId = localStorage.getItem("userId") || 1;

    estado.salvando = fetch(`/api/progress?usuarioId=${usuarioId}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            lessonId: Number(lessonId),
            xpGanho: estado.pontosGanhos,
            totalAcertos: estado.pontosGanhos,
            totalPerguntas: estado.pontosMaximos
        })
    }).catch((err) => console.error("Falha ao salvar progresso:", err));
}

// Espera o salvamento terminar antes de sair, senão o próximo nível
// pode aparecer ainda bloqueado.
function finalizarLicao(destino = urlNivel) {
    Promise.resolve(estado.salvando).finally(() => {
        window.location.href = destino;
    });
}

function renderResumo(c) {
    salvarProgresso();

    const aproveitamento = estado.pontosMaximos > 0
        ? Math.round((estado.pontosGanhos / estado.pontosMaximos) * 100)
        : 100;

    areaEl.innerHTML = `
        <div class="titulo-step titulo-largo">${c.titulo}</div>
        ${c.imagem ? `<img class="mascote mascote-grande" src="img/${c.imagem}" alt="Mascote">` : ""}
        <p class="xp-total">${c.texto}${estado.pontosGanhos} XP</p>
        <p class="texto-solto">Aproveitamento: ${aproveitamento}%</p>
        <button class="botao-continuar" onclick="avancarOuFinalizar()">
            ${temProximoStep() ? "Continuar" : "Voltar para o nível"}
        </button>
    `;
}

function renderConclusao(c, textoBotao, destino) {
    const arg = destino ? `'${destino}'` : "";
    areaEl.innerHTML = `
        <div class="card-licao centralizado">
            <div class="titulo-step">${c.titulo}</div>
            <p>${c.texto}</p>
            ${c.badge ? `<div class="badge-conquista">🏅 ${c.badge}</div>` : ""}
        </div>
        <button class="botao-continuar" onclick="avancarOuFinalizar(${arg})">
            ${temProximoStep() ? "Continuar" : textoBotao}
        </button>
    `;
}

const TIPOS_PERGUNTA = ["QUIZ", "JOGO"];

// Só mostra "voltar" se o step atual E o anterior não forem perguntas.
// Assim ninguém volta pra uma pergunta já respondida (nem pra ver a resposta,
// nem pra refazer e somar XP duas vezes).
function podeVoltar() {
    if (estado.stepAtual === 0) return false;
    const steps = estado.licao.steps;
    const atual = steps[estado.stepAtual].tipo;
    const anterior = steps[estado.stepAtual - 1].tipo;
    return !TIPOS_PERGUNTA.includes(atual) && !TIPOS_PERGUNTA.includes(anterior);
}

function atualizarBotaoVoltar() {
    document.getElementById("btn-voltar-step").style.visibility =
        podeVoltar() ? "visible" : "hidden";
}

function voltarStep() {
    if (!podeVoltar()) return;
    estado.stepAtual--;
    renderStep(estado.licao.steps[estado.stepAtual]);
}