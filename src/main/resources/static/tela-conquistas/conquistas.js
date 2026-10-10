// Tela de conquistas: busca medalhas e progresso na API e monta a tela.
// Usa o mesmo padrão das telas de níveis: userId salvo no localStorage + ?usuarioId= na URL.

const IMG = "/tela-conquistas/img/";

// Imagem e "rank" de cada medalha do catálogo (código vem do backend, MedalhaCodigo).
// Os nomes das imagens e das cores são os que já existiam na tela.
// Medalha de código desconhecido (criada depois no catálogo) usa a cinza/prata.
const VISUAL_POR_CODIGO = {
    PRIMEIRA_MISSAO:             { imagem: "conquistas (3).png", rank: "Cobre" },
    TERMINAR_NIVEL_80:           { imagem: "conquistas (3).png", rank: "Cobre" },
    TERMINAR_NIVEL_100:          { imagem: "conquistas.png",     rank: "Ouro"  },
    JOGO_FINAL_PONTUACAO_MAXIMA: { imagem: "conquistas.png",     rank: "Ouro"  },
    FINALIZAR_CURSO:             { imagem: "conquistas (4).png", rank: "Prata" },
    OFENSIVA_7_DIAS:             { imagem: "conquistas.png",     rank: "Ouro"  },
    OFENSIVA_15_DIAS:            { imagem: "conquistas.png",     rank: "Ouro"  },
    OFENSIVA_30_DIAS:            { imagem: "conquistas.png",     rank: "Ouro"  },
};
const VISUAL_PADRAO = { imagem: "conquistas (4).png", rank: "Prata" };
const ORDEM_RANK = ["Cobre", "Prata", "Ouro"]; // do menor para o maior

const usuarioId = localStorage.getItem("userId");

const el = {
    resumoMedalhas: document.getElementById("resumo-medalhas"),
    resumoPercentual: document.getElementById("resumo-percentual"),
    barra: document.getElementById("barra-medalhas"),
    statMedalhas: document.getElementById("stat-medalhas"),
    statRank: document.getElementById("stat-rank"),
    statAcertos: document.getElementById("stat-acertos"),
    estado: document.getElementById("estado-conquistas"),
    container: document.querySelector(".container"),
    rodape: document.querySelector(".container .rodape"),
};

// ---------------------------------------------------------------- API

async function buscarJson(url) {
    const res = await fetch(url);
    if (!res.ok) throw new Error("HTTP " + res.status);
    return res.json();
}

const api = {
    catalogo: () => buscarJson("/medalhas"),
    medalhasDoUsuario: (id) => buscarJson(`/usuarios/${id}/medalhas`),
    resumoProgresso: (id) => buscarJson(`/api/progress?usuarioId=${id}`),
};

// ---------------------------------------------------------------- tela

async function carregar() {
    if (!usuarioId) {
        window.location.href = "/login";
        return;
    }

    mostrarEstado("Carregando suas conquistas...");
    limparCards();

    try {
        const [catalogo, doUsuario, resumo] = await Promise.all([
            api.catalogo(),
            api.medalhasDoUsuario(usuarioId),
            // o % de acertos é secundário: se falhar, o resto da tela ainda funciona
            api.resumoProgresso(usuarioId).catch(() => null),
        ]);
        renderizar(catalogo, doUsuario, resumo);
    } catch (err) {
        console.error("Falha ao carregar conquistas:", err);
        el.resumoMedalhas.textContent = "Não foi possível carregar";
        el.resumoPercentual.textContent = "";
        mostrarEstado("Não foi possível carregar suas conquistas. Atualize a página para tentar de novo.");
    }
}

function renderizar(catalogo, doUsuario, resumo) {
    // código da medalha -> data em que foi concluída (só as concluídas contam)
    const conquistadas = new Map();
    doUsuario
        .filter((v) => v.concluida && v.medalha)
        .forEach((v) => conquistadas.set(v.medalha.id, v.updatedAt));

    const desbloqueadas = catalogo.filter((m) => conquistadas.has(m.id));
    const bloqueadas = catalogo.filter((m) => !conquistadas.has(m.id));

    // cabeçalho
    const total = catalogo.length;
    const qtd = desbloqueadas.length;
    const percentual = total > 0 ? Math.round((qtd * 100) / total) : 0;

    el.resumoMedalhas.textContent = `${qtd}/${total} medalhas`;
    el.resumoPercentual.textContent = `${percentual}%`;
    el.barra.value = percentual;
    el.statMedalhas.textContent = `${qtd}/${total}`;
    el.statRank.textContent = calcularRank(desbloqueadas);
    el.statAcertos.textContent = formatarAcertos(resumo);

    // cards
    limparCards();
    desbloqueadas.forEach((m) => adicionarCard(m, false, conquistadas.get(m.id)));
    bloqueadas.forEach((m) => adicionarCard(m, true));

    if (total === 0) {
        mostrarEstado("Nenhuma medalha disponível no momento.");
    } else if (qtd === 0) {
        mostrarEstado("Você ainda não desbloqueou nenhuma medalha. Conclua uma lição para ganhar a primeira!");
    } else {
        el.estado.hidden = true;
    }
}

function visualDa(medalha) {
    return VISUAL_POR_CODIGO[medalha.codigo] || VISUAL_PADRAO;
}

// Rank = a maior medalha já desbloqueada.
function calcularRank(desbloqueadas) {
    let melhor = -1;
    desbloqueadas.forEach((m) => {
        melhor = Math.max(melhor, ORDEM_RANK.indexOf(visualDa(m).rank));
    });
    return melhor >= 0 ? ORDEM_RANK[melhor] : "–";
}

function formatarAcertos(resumo) {
    if (!resumo || !resumo.totalPerguntas) return "–";
    return Math.round((resumo.totalAcertos * 100) / resumo.totalPerguntas) + "%";
}

// updatedAt chega como texto ISO (ou, dependendo da config do Jackson, como [ano, mês, dia, ...]).
function formatarData(valor) {
    if (!valor) return null;
    const data = Array.isArray(valor)
        ? new Date(valor[0], valor[1] - 1, valor[2])
        : new Date(valor);
    return isNaN(data) ? null : data.toLocaleDateString("pt-BR");
}

// Mesma marcação dos cards que existiam no HTML estático.
function adicionarCard(medalha, bloqueada, concluidaEm) {
    const visual = visualDa(medalha);

    const card = document.createElement("div");
    card.className = bloqueada ? "conquistas-bloqueadas" : "conquistas";

    const texto = document.createElement("div");
    texto.className = "conquista-texto";

    const titulo = document.createElement("h3");
    titulo.className = "titulo-conquistas";
    titulo.textContent = medalha.nome + (bloqueada ? " 🔒" : "");

    const descricao = document.createElement("p");
    descricao.className = "descricao-conquista";
    descricao.textContent = medalha.descricao || "";

    texto.append(titulo, descricao);

    const data = bloqueada ? null : formatarData(concluidaEm);
    if (data) {
        const quando = document.createElement("p");
        quando.className = "descricao-conquista";
        quando.textContent = "Desbloqueada em " + data;
        texto.append(quando);
    }

    const imagem = document.createElement("img");
    imagem.src = IMG + encodeURI(visual.imagem);
    imagem.alt = "medalha " + visual.rank.toLowerCase();
    imagem.className = "medalha-imagem";

    card.append(texto, imagem);
    el.container.insertBefore(card, el.rodape);
}

function limparCards() {
    el.container
        .querySelectorAll(".conquistas, .conquistas-bloqueadas")
        .forEach((c) => c.remove());
}

function mostrarEstado(mensagem) {
    el.estado.textContent = mensagem;
    el.estado.hidden = false;
}

// Ao voltar para a tela pelo botão "voltar" do navegador o cache pode mostrar dados antigos
// (ex.: acabou de concluir uma lição e ganhou medalha). Recarrega nesse caso.
window.addEventListener("pageshow", (e) => {
    if (e.persisted) carregar();
});

carregar();
