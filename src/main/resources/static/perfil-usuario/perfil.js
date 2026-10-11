// Tela de perfil: busca os dados do usuário logado na API e preenche a página.
// Rodar sempre com o servidor Spring no ar; o login cria a sessão (cookie).

const XP_POR_NIVEL = 1000;                 // regra assumida: 1000 XP por nível (ajuste se precisar)
const IMG_MEDALHAS = "/tela-conquistas/img/";
const IMAGEM_POR_CODIGO = {                // mesmas imagens da tela de conquistas
    PRIMEIRA_MISSAO: "conquistas (3).png",
    TERMINAR_NIVEL_80: "conquistas (3).png",
    TERMINAR_NIVEL_100: "conquistas.png",
    JOGO_FINAL_PONTUACAO_MAXIMA: "conquistas.png",
    FINALIZAR_CURSO: "conquistas (4).png",
    OFENSIVA_7_DIAS: "conquistas.png",
    OFENSIVA_15_DIAS: "conquistas.png",
    OFENSIVA_30_DIAS: "conquistas.png",
};
const IMAGEM_PADRAO = "conquistas (4).png";

const $ = (id) => document.getElementById(id);
const setTexto = (id, texto) => { const el = $(id); if (el) el.textContent = texto; };

async function buscarJson(url) {
    const res = await fetch(url);
    if (res.status === 401) {              // sessão expirada: volta para o login
        localStorage.removeItem("userId");
        window.location.href = "/TelasIniciais/login.html";
        throw new Error("Não autenticado");
    }
    if (!res.ok) throw new Error(`${url} respondeu ${res.status}`);
    return res.json();
}

function classificar(nivel) {
    if (nivel >= 6) return "Avançado";
    if (nivel >= 3) return "Intermediário";
    return "Iniciante";
}

function preencherUsuario(usuario) {
    const xp = usuario.xpTotal || 0;
    const nivel = Math.floor(xp / XP_POR_NIVEL) + 1;
    const xpNoNivel = xp % XP_POR_NIVEL;

    setTexto("nome", usuario.nome);               // troca o "NOME" pelo nome do usuário logado
    setTexto("nivel", "Nível " + nivel);
    $("barra-xp").max = XP_POR_NIVEL;
    $("barra-xp").value = xpNoNivel;
    setTexto("xp-texto", `${xpNoNivel} / ${XP_POR_NIVEL} XP para o próximo nível`);
    setTexto("xp-pct", Math.round((xpNoNivel * 100) / XP_POR_NIVEL) + "%");
    setTexto("xp-total", xp + " XP");
    setTexto("classificacao", classificar(nivel));
}

function preencherCurso(resumo, niveis) {
    const total = niveis.reduce((soma, n) => soma + (n.licoes ? n.licoes.length : 0), 0);
    const feitas = resumo.licoesConcluidas;
    const pct = total ? Math.round((feitas * 100) / total) : 0;

    // Nível atual = o último nível que já está desbloqueado para o usuário.
    const desbloqueados = niveis.filter((n) => !n.bloqueado);
    const atual = desbloqueados[desbloqueados.length - 1];
    const concluido = total > 0 && feitas >= total;

    setTexto("licoes", feitas);
    $("barra-curso").value = pct;
    setTexto("curso-pct", pct + "%");
    setTexto("curso-texto", `${feitas} de ${total} lições concluídas`);
    setTexto("curso-nome", concluido
        ? "SecureByte — curso concluído! 🎉"
        : atual ? `SecureByte — Nível ${atual.ordem}: ${atual.titulo}` : "SecureByte");
}

function preencherMedalhas(medalhas) {
    const conquistadas = medalhas.filter((m) => m.concluida).slice(0, 3);
    const figura = $("medalhas");
    figura.innerHTML = "";

    if (conquistadas.length === 0) {
        figura.textContent = "Nenhuma conquista ainda.";
        return;
    }
    conquistadas.forEach((m) => {
        const img = document.createElement("img");
        img.src = IMG_MEDALHAS + (IMAGEM_POR_CODIGO[m.medalha.codigo] || IMAGEM_PADRAO);
        img.alt = m.medalha.nome;
        img.title = m.medalha.nome;
        img.width = 90;
        figura.appendChild(img);
    });
}

async function carregarPerfil() {
    const usuario = await buscarJson("/usuarios/me");
    localStorage.setItem("userId", usuario.id);
    preencherUsuario(usuario);                       // nome e XP já aparecem

    // O resto é independente: se uma chamada falhar, as outras seguem.
    const id = usuario.id;
    const [resumo, niveis, medalhas] = await Promise.allSettled([
        buscarJson(`/api/progress?usuarioId=${id}`),
        buscarJson(`/api/levels?usuarioId=${id}`),
        buscarJson(`/usuarios/${id}/medalhas`),
    ]);

    if (resumo.status === "fulfilled" && niveis.status === "fulfilled") {
        preencherCurso(resumo.value, niveis.value);
    }
    if (medalhas.status === "fulfilled") {
        preencherMedalhas(medalhas.value);
    } else {
        setTexto("medalhas", "Nenhuma conquista ainda.");
    }
}

carregarPerfil().catch(console.error);