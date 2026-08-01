// progresso.js - controla o progresso do usuário (missões concluídas) e as
// regras de bloqueio de nível.
//
// É a única fonte de verdade sobre progresso: tudo fica salvo no
// localStorage do navegador, então qualquer tela (index.html, trilha.html,
// missao.html) consegue ler e gravar sem precisar passar nada por query
// string ou por variável global entre páginas.
//
// Uso esperado: incluir este script ANTES dos scripts de cada tela
// (parecido com o que já acontece hoje com js/app.js e window.Renderers).
(function () {
    // Chave usada no localStorage. Prefixada com "secubyte:" pra não colidir
    // com outra coisa que o navegador possa guardar nesse mesmo domínio.
    const CHAVE_ARMAZENAMENTO = "secubyte:progresso";

    // Cache em memória do trilhas-index.json, carregado uma vez por
    // carregarIndice() e reaproveitado por quem chamar de novo depois -
    // evita repetir o fetch toda hora que uma função de regra precisa saber
    // a ordem das trilhas.
    let indiceCache = null;

    // ---------------------------------------------------------------------
    // Leitura e escrita "cruas" no localStorage
    // ---------------------------------------------------------------------

    // Lê o progresso salvo. Se ainda não existir nada (primeira visita da
    // pessoa), devolve uma estrutura vazia em vez de null - assim quem
    // chama essa função não precisa ficar checando "existe ou não existe"
    // toda vez.
    function lerEstado() {
        try {
            const bruto = window.localStorage.getItem(CHAVE_ARMAZENAMENTO);
            if (!bruto) {
                return { missoesConcluidas: [] };
            }

            const dados = JSON.parse(bruto);

            // Garante que o campo sempre exista, mesmo se o JSON salvo
            // estiver de um formato antigo ou corrompido.
            if (!Array.isArray(dados.missoesConcluidas)) {
                dados.missoesConcluidas = [];
            }

            return dados;
        } catch (erro) {
            // localStorage pode falhar (aba anônima, quota cheia, etc).
            // Nesses casos seguimos com progresso vazio em vez de quebrar a
            // tela inteira.
            console.warn("Não foi possível ler o progresso salvo:", erro);
            return { missoesConcluidas: [] };
        }
    }

    // Grava o estado inteiro no localStorage, sobrescrevendo o que
    // estiver lá.
    function salvarEstado(estado) {
        try {
            window.localStorage.setItem(CHAVE_ARMAZENAMENTO, JSON.stringify(estado));
        } catch (erro) {
            console.warn("Não foi possível salvar o progresso:", erro);
        }
    }

    // ---------------------------------------------------------------------
    // API pública: missões
    // ---------------------------------------------------------------------

    // Devolve um Set com os ids de todas as missões já concluídas,
    // ex.: Set { "1-1", "1-2", "2-1" }.
    // Um Set é mais prático que um array puro pra perguntar "essa missão já
    // foi feita?" (missoesConcluidas().has(id)) sem precisar de indexOf.
    function missoesConcluidas() {
        return new Set(lerEstado().missoesConcluidas);
    }

    // Marca uma missão como concluída e já salva no localStorage.
    // Não faz nada se a missão já estava marcada, pra não duplicar no
    // array (e não precisar de lógica extra em quem chama).
    function marcarMissaoConcluida(missaoId) {
        const estado = lerEstado();

        if (!estado.missoesConcluidas.includes(missaoId)) {
            estado.missoesConcluidas.push(missaoId);
            salvarEstado(estado);
        }
    }

    // ---------------------------------------------------------------------
    // API pública: trilhas / índice
    // ---------------------------------------------------------------------

    // Busca (com cache) o conteúdo de trilhas-index.json.
    // Recebe o caminho como parâmetro porque index.html, trilha.html e
    // missao.html podem estar em profundidades diferentes de pasta; por
    // padrão assume que o arquivo está ao lado da página atual.
    function carregarIndice(caminho) {
        if (indiceCache) {
            return Promise.resolve(indiceCache);
        }

        return fetch(caminho || "trilhas-index.json")
            .then((res) => res.json())
            .then((indice) => {
                indiceCache = indice;
                return indice;
            });
    }

    // Verifica se TODAS as missões de uma trilha já foram concluídas.
    // Recebe o objeto da trilha inteiro (como vem em trilhas-index.json),
    // não só o número, pra essa função não precisar buscar o índice de
    // novo por conta própria.
    function trilhaConcluida(trilha) {
        const concluidas = missoesConcluidas();
        const missoes = trilha.missoes || [];

        // Trilha sem nenhuma missão cadastrada não conta como "concluída",
        // pra não liberar a próxima trilha por engano.
        if (missoes.length === 0) {
            return false;
        }

        return missoes.every((missao) => concluidas.has(missao.id));
    }

    // Regra central de bloqueio de nível:
    // - a primeira trilha da lista está sempre desbloqueada;
    // - qualquer outra trilha só desbloqueia quando a trilha IMEDIATAMENTE
    //   anterior estiver 100% concluída.
    //
    // Recebe a lista inteira de trilhas (na ordem de trilhas-index.json) e
    // o número da trilha que se quer checar, já que a regra depende de
    // quem vem antes dela.
    function nivelDesbloqueado(trilhas, numeroTrilha) {
        const indiceAtual = trilhas.findIndex((t) => t.numero === numeroTrilha);

        // Trilha não encontrada, ou é a primeira da lista: sempre liberada.
        if (indiceAtual <= 0) {
            return true;
        }

        const trilhaAnterior = trilhas[indiceAtual - 1];
        return trilhaConcluida(trilhaAnterior);
    }

    // ---------------------------------------------------------------------
    // Utilitário
    // ---------------------------------------------------------------------

    // Apaga todo o progresso salvo. Útil para um botão de "recomeçar do
    // zero" nas telas, ou para testar o fluxo de bloqueio manualmente pelo
    // console do navegador.
    function resetarProgresso() {
        try {
            window.localStorage.removeItem(CHAVE_ARMAZENAMENTO);
        } catch (erro) {
            console.warn("Não foi possível apagar o progresso:", erro);
        }
    }

    // Expõe tudo num único objeto global "Progresso", seguindo o mesmo
    // padrão que o projeto já usa em window.Renderers.
    window.Progresso = {
        carregarIndice,
        missoesConcluidas,
        marcarMissaoConcluida,
        trilhaConcluida,
        nivelDesbloqueado,
        resetarProgresso
    };
})();