fetch("niveis.json")
    .then(response => response.json())
    .then(niveis => {

        const params = new URLSearchParams(window.location.search);

        const numeroNivel = params.get("nivel") || "1";

        const nivel = niveis[numeroNivel];

        const container = document.getElementById("conteudo");

        if (!nivel) {

            container.innerHTML = "<h1>Nível não encontrado.</h1>";

            return;

        }

        renderNivel(nivel, container);

    });