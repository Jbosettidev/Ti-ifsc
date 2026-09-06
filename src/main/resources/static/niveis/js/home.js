// niveis/js/home.js - monta a lista de níveis (trilhas) na home, buscando
// da API do backend. Ao clicar num nível, navega pra nivel.html com o id
// dele — não expande mais nada aqui, isso agora é responsabilidade da
// tela do nível.

const listaEl = document.getElementById("trilhas-lista");

fetch("/api/levels")
    .then((res) => res.json())
    .then((niveis) => montarTrilhas(niveis || []))
    .catch((err) => {
        console.error(err);
        listaEl.innerHTML = "<p class='carregando'>Não foi possível carregar as trilhas.</p>";
    });

function montarTrilhas(trilhas) {
    listaEl.innerHTML = "";

    trilhas.forEach((trilha) => {

        const card = document.createElement("section");
        card.className = "trilha-card";
        if (trilha.bloqueado) card.classList.add("bloqueada");

        const cabecalho = document.createElement("div");
        cabecalho.className = "trilha-cabecalho";

        const titulo = document.createElement("h4");
        titulo.className = "trilha-titulo";
        titulo.textContent = "Nível " + trilha.ordem + ": " + trilha.titulo;
        cabecalho.appendChild(titulo);

        // Se o nível estiver bloqueado, mostra um ícone de cadeado ao lado
        // do título em vez de deixar o card clicável.
        if (trilha.bloqueado) {
            const cadeado = document.createElement("span");
            cadeado.className = "material-symbols-outlined cadeado";
            cadeado.textContent = "lock";
            cabecalho.appendChild(cadeado);
        }

        card.appendChild(cabecalho);

        if (trilha.descricao) {
            const descricao = document.createElement("p");
            descricao.className = "trilha-descricao";
            descricao.textContent = trilha.descricao;
            card.appendChild(descricao);
        }

        // O card inteiro é clicável (não só um botão dentro dele) —
        // fica mais fácil de tocar no celular.
        if (!trilha.bloqueado) {
            card.addEventListener("click", () => {
                window.location.href = `niveis/nivel.html?levelId=${trilha.id}`;
            });
        }

        listaEl.appendChild(card);
    });
}