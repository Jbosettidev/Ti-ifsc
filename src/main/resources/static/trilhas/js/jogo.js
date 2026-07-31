// jogo.js - dispatcher para os "moldes" de desafio reutilizáveis.
// Cada missão só muda os dados (secao.subtipo + dados); a mecânica é a mesma
// pra todas as missões que usam o mesmo molde.

function renderJogo(secao) {
    const section = document.createElement("section");
    section.className = "secao-jogo";

    if (secao.instrucao) {
        const h2 = document.createElement("h2");
        h2.textContent = secao.instrucao;
        section.appendChild(h2);
    }

    const moldes = {
        "classificar-2-categorias": renderClassificar,
        "classificar-3-categorias": renderClassificar,
        "classificar-4-categorias": renderClassificar,
        "cenario-escolha": renderCenarioEscolha,
        "cenario-multipla-escolha": renderCenarioMultiplaEscolha,
        "arrastar-cronometro": renderArrastarCronometro,
        "ordenar-sequencia": renderOrdenarSequencia,
        "identificar-em-lista": renderIdentificarEmLista,
        "simulacao-email": renderSimulacaoEmail
    };

    const fn = moldes[secao.subtipo];
    if (fn) {
        section.appendChild(fn(secao));
    } else {
        const aviso = document.createElement("p");
        aviso.textContent = "Molde de jogo \"" + secao.subtipo + "\" ainda não implementado.";
        section.appendChild(aviso);
    }

    return section;
}

// --- Molde: classificar itens em N categorias (clique, não drag real) ---
function renderClassificar(secao) {
    const container = document.createElement("div");
    container.className = "jogo-classificar";

    const categorias = secao.categorias || [];
    const colunas = document.createElement("div");
    colunas.className = "jogo-colunas";
    const colunaPorNome = {};

    categorias.forEach((cat) => {
        const col = document.createElement("div");
        col.className = "jogo-coluna";
        const titulo = document.createElement("h4");
        titulo.textContent = cat;
        col.appendChild(titulo);
        colunas.appendChild(col);
        colunaPorNome[cat] = col;
    });

    const itensDiv = document.createElement("div");
    itensDiv.className = "jogo-itens";
    const feedback = document.createElement("p");
    feedback.className = "jogo-feedback";

    (secao.itens || []).forEach((item) => {
        const btn = document.createElement("button");
        btn.type = "button";
        btn.className = "jogo-item";
        btn.textContent = item.texto;

        btn.addEventListener("click", () => {
            if (btn.disabled) return;
            colunas.querySelectorAll(".jogo-coluna").forEach((col) => col.classList.add("selecionavel"));

            const listeners = [];
            categorias.forEach((cat) => {
                const col = colunaPorNome[cat];
                const handler = () => {
                    const correto = cat === item.categoria;
                    btn.disabled = true;
                    btn.classList.add(correto ? "correto" : "incorreto");
                    feedback.textContent = correto
                        ? "Certo!" + (item.observacao ? " " + item.observacao : "")
                        : "Era \"" + item.categoria + "\"." + (item.observacao ? " " + item.observacao : "");
                    colunas.querySelectorAll(".jogo-coluna").forEach((c) => c.classList.remove("selecionavel"));
                    listeners.forEach(({ el, fn }) => el.removeEventListener("click", fn));
                };
                col.addEventListener("click", handler);
                listeners.push({ el: col, fn: handler });
            });
        });

        itensDiv.appendChild(btn);
    });

    if (secao.cronometro) {
        const timer = document.createElement("div");
        timer.className = "jogo-timer";
        let tempo = secao.tempoSegundos || 15;
        timer.textContent = "Tempo: " + tempo + "s";
        container.appendChild(timer);

        const intervalo = setInterval(() => {
            tempo -= 1;
            timer.textContent = "Tempo: " + Math.max(tempo, 0) + "s";
            if (tempo <= 0) {
                clearInterval(intervalo);
                itensDiv.querySelectorAll("button").forEach((b) => (b.disabled = true));
                timer.textContent = "Tempo esgotado!";
            }
        }, 1000);
    }

    container.appendChild(itensDiv);
    container.appendChild(colunas);
    container.appendChild(feedback);
    return container;
}

// --- Molde: cenário com 2 opções, em rodadas sequenciais ---
function renderCenarioEscolha(secao) {
    const container = document.createElement("div");
    container.className = "jogo-cenario-escolha";
    const rodadas = secao.rodadas || [];
    let indice = 0;

    function mostrar() {
        container.innerHTML = "";

        if (indice >= rodadas.length) {
            const fim = document.createElement("p");
            fim.className = "jogo-fim";
            fim.textContent = "Desafio concluído!";
            container.appendChild(fim);
            return;
        }

        const rodada = rodadas[indice];
        const situacao = document.createElement("p");
        situacao.className = "jogo-situacao";
        situacao.textContent = rodada.situacao;
        container.appendChild(situacao);

        const botoes = document.createElement("div");
        botoes.className = "jogo-botoes";
        const feedback = document.createElement("p");
        feedback.className = "jogo-feedback";

        ["opcaoA", "opcaoB"].forEach((chave) => {
            const btn = document.createElement("button");
            btn.type = "button";
            btn.textContent = rodada[chave];
            btn.addEventListener("click", () => {
                const correto = chave === rodada.respostaCorreta;
                Array.from(botoes.children).forEach((b) => (b.disabled = true));
                btn.classList.add(correto ? "correto" : "incorreto");
                feedback.textContent = rodada.explicacao || (correto ? "Correto!" : "Não foi dessa vez.");
                setTimeout(() => {
                    indice += 1;
                    mostrar();
                }, 1800);
            });
            botoes.appendChild(btn);
        });

        container.appendChild(botoes);
        container.appendChild(feedback);
    }

    mostrar();
    return container;
}

// --- Molde: cenário com múltipla escolha (3 opções), em rodadas ---
function renderCenarioMultiplaEscolha(secao) {
    const container = document.createElement("div");
    container.className = "jogo-cenario-multipla";
    const rodadas = secao.rodadas || [];
    let indice = 0;

    function mostrar() {
        container.innerHTML = "";

        if (indice >= rodadas.length) {
            if (secao.perguntaBonus) {
                container.appendChild(montarBonus(secao.perguntaBonus));
                return;
            }
            const fim = document.createElement("p");
            fim.className = "jogo-fim";
            fim.textContent = "Desafio concluído!";
            container.appendChild(fim);
            return;
        }

        const rodada = rodadas[indice];
        const situacao = document.createElement("p");
        situacao.className = "jogo-situacao";
        situacao.textContent = rodada.situacao;
        container.appendChild(situacao);

        const opcoesDiv = document.createElement("div");
        opcoesDiv.className = "jogo-botoes";
        const feedback = document.createElement("p");
        feedback.className = "jogo-feedback";

        rodada.opcoes.forEach((opcao) => {
            const btn = document.createElement("button");
            btn.type = "button";
            btn.textContent = opcao;
            btn.addEventListener("click", () => {
                const correto = opcao === rodada.respostaCorreta;
                Array.from(opcoesDiv.children).forEach((b) => (b.disabled = true));
                btn.classList.add(correto ? "correto" : "incorreto");
                feedback.textContent = correto ? "Correto!" : "Era: " + rodada.respostaCorreta;
                setTimeout(() => {
                    indice += 1;
                    mostrar();
                }, 1800);
            });
            opcoesDiv.appendChild(btn);
        });

        container.appendChild(opcoesDiv);
        container.appendChild(feedback);
    }

    function montarBonus(bonus) {
        const wrapper = document.createElement("div");
        const titulo = document.createElement("p");
        titulo.className = "jogo-situacao";
        titulo.textContent = "Bônus: " + bonus.pergunta;
        wrapper.appendChild(titulo);

        const opcoesDiv = document.createElement("div");
        opcoesDiv.className = "jogo-botoes";
        bonus.opcoes.forEach((opcao) => {
            const btn = document.createElement("button");
            btn.type = "button";
            btn.textContent = opcao;
            btn.addEventListener("click", () => {
                const correto = opcao === bonus.respostaCorreta;
                Array.from(opcoesDiv.children).forEach((b) => (b.disabled = true));
                btn.classList.add(correto ? "correto" : "incorreto");
            });
            opcoesDiv.appendChild(btn);
        });
        wrapper.appendChild(opcoesDiv);
        return wrapper;
    }

    mostrar();
    return container;
}

// --- Molde: arrastar (clique) com cronômetro - ex. "Salve os Arquivos" ---
function renderArrastarCronometro(secao) {
    const container = document.createElement("div");
    container.className = "jogo-arrastar";

    const timer = document.createElement("div");
    timer.className = "jogo-timer";
    let tempo = secao.tempoSegundos || 15;
    timer.textContent = "Tempo: " + tempo + "s";
    container.appendChild(timer);

    const itensDiv = document.createElement("div");
    itensDiv.className = "jogo-itens";
    const resultado = document.createElement("p");
    resultado.className = "jogo-feedback";

    let salvos = 0;
    let jogoAcabou = false;
    const totalImportantes = (secao.itens || []).filter((i) => i.importante).length;

    (secao.itens || []).forEach((item) => {
        const btn = document.createElement("button");
        btn.type = "button";
        btn.className = "jogo-item";
        btn.textContent = item.nome;
        btn.addEventListener("click", () => {
            if (jogoAcabou || btn.disabled) return;
            btn.disabled = true;
            if (item.importante) {
                btn.classList.add("correto");
                btn.textContent = (secao.feedback?.salvo || "\u2705 Salvo") + ": " + item.nome;
                salvos += 1;
            } else {
                btn.classList.add("neutro");
                btn.textContent = "Não era prioridade: " + item.nome;
            }
        });
        itensDiv.appendChild(btn);
    });

    const intervalo = setInterval(() => {
        tempo -= 1;
        timer.textContent = "Tempo: " + Math.max(tempo, 0) + "s";
        if (tempo <= 0) {
            clearInterval(intervalo);
            jogoAcabou = true;
            timer.textContent = "Ataque!";
            itensDiv.querySelectorAll("button").forEach((b) => {
                if (!b.disabled) {
                    b.disabled = true;
                    b.classList.add("incorreto");
                    b.textContent = (secao.feedback?.perdido || "\uD83D\uDD12 Perdido");
                }
            });
            resultado.textContent = "Você salvou " + salvos + " de " + totalImportantes + " arquivos importantes.";
        }
    }, 1000);

    container.appendChild(itensDiv);
    container.appendChild(resultado);
    return container;
}

// --- Molde: ordenar sequência (clique em ordem) ---
function renderOrdenarSequencia(secao) {
    const container = document.createElement("div");
    container.className = "jogo-ordenar";

    const escolhidos = [];
    const listaEscolhida = document.createElement("ol");
    listaEscolhida.className = "jogo-ordem-escolhida";

    const opcoesDiv = document.createElement("div");
    opcoesDiv.className = "jogo-itens";
    const feedback = document.createElement("p");
    feedback.className = "jogo-feedback";

    const itens = [...(secao.itensEmbaralhados || [])];

    itens.forEach((texto) => {
        const btn = document.createElement("button");
        btn.type = "button";
        btn.className = "jogo-item";
        btn.textContent = texto;
        btn.addEventListener("click", () => {
            if (btn.disabled) return;
            btn.disabled = true;
            btn.classList.add("selecionado");
            escolhidos.push(texto);
            const li = document.createElement("li");
            li.textContent = texto;
            listaEscolhida.appendChild(li);

            if (escolhidos.length === itens.length) {
                const correto = JSON.stringify(escolhidos) === JSON.stringify(secao.ordemCorreta || []);
                feedback.textContent = correto
                    ? "Ordem correta!"
                    : "Quase lá - confira a ordem certa: " + (secao.ordemCorreta || []).join(" → ");
                feedback.classList.add(correto ? "ok" : "erro");
            }
        });
        opcoesDiv.appendChild(btn);
    });

    container.appendChild(opcoesDiv);
    container.appendChild(listaEscolhida);
    container.appendChild(feedback);
    return container;
}

// --- Molde: identificar item suspeito numa lista ---
function renderIdentificarEmLista(secao) {
    const container = document.createElement("div");
    container.className = "jogo-identificar";
    const feedback = document.createElement("p");
    feedback.className = "jogo-feedback";

    (secao.itens || []).forEach((item) => {
        const btn = document.createElement("button");
        btn.type = "button";
        btn.className = "jogo-item";
        btn.textContent = item.texto;
        btn.addEventListener("click", () => {
            Array.from(container.querySelectorAll("button")).forEach((b) => (b.disabled = true));
            const correto = !!item.suspeito;
            btn.classList.add(correto ? "correto" : "incorreto");
            feedback.textContent = correto
                ? "Correto! " + (item.motivo || "")
                : "Esse não era o suspeito.";
        });
        container.appendChild(btn);
    });

    container.appendChild(feedback);
    return container;
}

// --- Molde: simulação de e-mail (manter ou deletar) ---
function renderSimulacaoEmail(secao) {
    const container = document.createElement("div");
    container.className = "jogo-email";

    (secao.cenarios || []).forEach((cenario) => {
        const card = document.createElement("div");
        card.className = "email-card-jogo";

        const remetente = document.createElement("p");
        remetente.className = "email-remetente-jogo";
        remetente.textContent = "De: " + cenario.remetente;

        const assunto = document.createElement("h3");
        assunto.textContent = cenario.assunto;

        const corpo = document.createElement("p");
        corpo.textContent = cenario.corpo;

        const botoes = document.createElement("div");
        botoes.className = "jogo-botoes";
        const feedback = document.createElement("p");
        feedback.className = "jogo-feedback";

        const btnManter = document.createElement("button");
        btnManter.type = "button";
        btnManter.textContent = "Manter";
        const btnDeletar = document.createElement("button");
        btnDeletar.type = "button";
        btnDeletar.textContent = "Deletar";

        function responder(acaoFoiDeletar) {
            Array.from(botoes.children).forEach((b) => (b.disabled = true));
            const acertou = acaoFoiDeletar === cenario.ehFraude;
            (acaoFoiDeletar ? btnDeletar : btnManter).classList.add(acertou ? "correto" : "incorreto");
            feedback.textContent = acertou ? cenario.feedbackCorreto : cenario.feedbackIncorreto;
        }

        btnManter.addEventListener("click", () => responder(false));
        btnDeletar.addEventListener("click", () => responder(true));

        botoes.appendChild(btnManter);
        botoes.appendChild(btnDeletar);

        card.appendChild(remetente);
        card.appendChild(assunto);
        card.appendChild(corpo);
        card.appendChild(botoes);
        card.appendChild(feedback);
        container.appendChild(card);
    });

    return container;
}

window.Renderers = window.Renderers || {};
window.Renderers.jogo = renderJogo;
