fetch("niveis.json")
    .then(res => res.json())
    .then(dados => {

        const params = new URLSearchParams(window.location.search);
        const numeroNivel = params.get("nivel");
        const nivel = dados[numeroNivel];
        const container = document.getElementById("conteudo");

        nivel.secoes.forEach(secao => {
            const section = document.createElement("section");
            if (secao.classe) section.classList.add(secao.classe);

            if (secao.titulo) {
                const h1 = document.createElement("h1");
                h1.textContent = secao.titulo;
                section.appendChild(h1);
            }

            if (secao.texto) {
                const p = document.createElement("p");
                p.textContent = secao.texto;
                section.appendChild(p);
            }

            if (secao.filhos) {
                const wrapper = document.createElement("div");
                wrapper.classList.add(secao.wrapper_classe ?? "wrapper");;

                secao.filhos.forEach(filho => {
                    const el = document.createElement(filho.tipo);
                    if (filho.classe) el.classList.add(filho.classe);

                    if (filho.titulo) {
                        const h2 = document.createElement("h2");
                        h2.textContent = filho.titulo;
                        el.appendChild(h2);
                    }

                    if (filho.texto) {
                        const p = document.createElement("p");
                        p.textContent = filho.texto;
                        el.appendChild(p);
                    }

                    if (filho.imagem) {
                        const img = document.createElement("img");
                        img.src = filho.imagem;
                        el.appendChild(img);
                    }

                    wrapper.appendChild(el);
                });

                section.appendChild(wrapper);
            }

            container.appendChild(section);
        });

    });