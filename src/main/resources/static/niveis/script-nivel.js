const params = new URLSearchParams(window.location.search);
const numeroNivel = Number(params.get("nivel"));

// se a pessoa nao concluiu o nivel anterior, ela é bloqueada de entrar no proximo
if (numeroNivel > 1) {
    const nivelAnterior = numeroNivel - 1;
    const liberado = localStorage.getItem(`nivel${nivelAnterior}`);

    if (liberado !== "concluido") {
        alert("Complete o nível anterior primeiro!");
        window.location.href = "index.html";
    }
}


fetch(`niveis json/nivel${numeroNivel}.json`) //pega os dados
    .then(res => res.json())
    .then(nivel => {

        const container = document.getElementById("conteudo");
        document.title = nivel.titulo;

        function criarElemento(elemento) {

            // tipos especiais que têm sua própria função de montagem
            switch (elemento.tipo) {
                case "quiz-final":
                    return criarQuizFinal(elemento.questoes);
                case "quiz":
                    return criarQuizSimples(elemento);
                case "quiz-multipla":
                    return criarQuizMultipla(elemento);
                case "dragdrop":
                    return criarDragDrop(elemento);
                case "ul":
                    return criarLista(elemento);
                case "video":
                    return criarVideo(elemento);
            }

            const el = document.createElement(elemento.tipo);

            if (elemento.classe)
                el.classList.add(elemento.classe);

            if (elemento.texto)
                el.textContent = elemento.texto;

            if (elemento.src)
                el.src = elemento.src;

            if (elemento.alt)
                el.alt = elemento.alt;

            if (elemento.href)
                el.href = elemento.href;

            // cards (div com titulo + texto, sem "filhos" no JSON)
            if (elemento.titulo || elemento.texto) {
                if (elemento.titulo) {
                    const tituloEl = document.createElement("h4");
                    tituloEl.classList.add("card-titulo");
                    tituloEl.textContent = elemento.titulo;
                    el.appendChild(tituloEl);
                }
                if (elemento.texto) {
                    // já foi usado como textContent acima, então
                    // troca para não perder o título
                    el.textContent = "";
                    const textoEl = document.createElement("p");
                    textoEl.classList.add("card-texto");
                    textoEl.textContent = elemento.texto;
                    el.appendChild(textoEl);
                }
            }

            if (elemento.filhos) {
                elemento.filhos.forEach(filho => {
                    el.appendChild(criarElemento(filho));
                });
            }

            return el;
        }

        // funcao pra criar listas
        function criarLista(elemento) {
            const ul = document.createElement("ul");

            if (elemento.classe)
                ul.classList.add(elemento.classe);

            (elemento.itens || []).forEach(item => {
                const li = document.createElement("li");
                li.textContent = item;
                ul.appendChild(li);
            });

            return ul;
        }

        // ---------- vídeo do YouTube ----------
        function criarVideo(elemento) {
            const wrapper = document.createElement("div");
            wrapper.classList.add("video-wrapper");

            if (elemento.titulo) {
                const titulo = document.createElement("h4");
                titulo.textContent = elemento.titulo;
                wrapper.appendChild(titulo);
            }

            if (elemento.src) {
                // vídeo local
                const video = document.createElement("video");
                video.src = elemento.src;
                video.controls = true;
                wrapper.appendChild(video);
            } else if (elemento.youtube && elemento.youtube !== "LINK_YOUTUBE") {
                // vídeo do youtube via iframe
                const iframe = document.createElement("iframe");
                iframe.src = elemento.youtube.replace("watch?v=", "embed/");
                iframe.width = "100%";
                iframe.height = "360";
                iframe.allowFullscreen = true;
                iframe.frameBorder = "0";
                wrapper.appendChild(iframe);
            } else {
                const aviso = document.createElement("p");
                aviso.textContent = "(vídeo ainda não configurado)";
                aviso.classList.add("video-pendente");
                wrapper.appendChild(aviso);
            }

            return wrapper;
        }

        // ---------- quiz de uma pergunta só ----------
        function criarQuizSimples(elemento) {
            const box = document.createElement("div");
            box.classList.add("quiz-simples");

            const pergunta = document.createElement("h3");
            pergunta.textContent = elemento.pergunta;
            box.appendChild(pergunta);

            const resultado = document.createElement("p");
            resultado.classList.add("quiz-resultado");

            elemento.alternativas.forEach((alt, i) => {
                const botao = document.createElement("button");
                botao.textContent = alt;
                botao.classList.add("alternativa");

                botao.onclick = () => {
                    const botoes = box.querySelectorAll("button");
                    botoes.forEach(b => b.disabled = true);

                    if (i === elemento.correta) {
                        botao.classList.add("correta");
                        resultado.textContent = "✅ Resposta certa!";
                    } else {
                        botao.classList.add("errada");
                        botoes[elemento.correta].classList.add("correta");
                        resultado.textContent = "❌ Resposta errada.";
                    }
                };

                box.appendChild(botao);
            });

            box.appendChild(resultado);
            return box;
        }

        // ---------- quiz de múltipla escolha (mais de uma correta) ----------
        function criarQuizMultipla(elemento) {
            const box = document.createElement("div");
            box.classList.add("quiz-multipla");

            const pergunta = document.createElement("h3");
            pergunta.textContent = elemento.pergunta;
            box.appendChild(pergunta);

            if (elemento.instrucao) {
                const instrucao = document.createElement("p");
                instrucao.classList.add("quiz-instrucao");
                instrucao.textContent = elemento.instrucao;
                box.appendChild(instrucao);
            }

            const selecionadas = new Set();

            elemento.alternativas.forEach((alt, i) => {
                const botao = document.createElement("button");
                botao.textContent = alt;
                botao.classList.add("alternativa");

                botao.onclick = () => {
                    if (selecionadas.has(i)) {
                        selecionadas.delete(i);
                        botao.classList.remove("selecionada");
                    } else {
                        selecionadas.add(i);
                        botao.classList.add("selecionada");
                    }
                };

                box.appendChild(botao);
            });

            const confirmar = document.createElement("button");
            confirmar.textContent = "Confirmar";
            confirmar.classList.add("confirmar");

            const resultado = document.createElement("p");
            resultado.classList.add("quiz-resultado");

            confirmar.onclick = () => {
                const botoes = box.querySelectorAll(".alternativa");
                botoes.forEach(b => b.disabled = true);
                confirmar.disabled = true;

                const corretas = new Set(elemento.corretas);
                let acertou = corretas.size === selecionadas.size &&
                    [...corretas].every(i => selecionadas.has(i));

                botoes.forEach((b, i) => {
                    if (corretas.has(i)) b.classList.add("correta");
                    else if (selecionadas.has(i)) b.classList.add("errada");
                });

                resultado.textContent = acertou
                    ? "✅ Você acertou!"
                    : "❌ Não foi dessa vez.";
            };

            box.appendChild(confirmar);
            box.appendChild(resultado);
            return box;
        }

        // ---------- drag and drop ----------
        function criarDragDrop(elemento) {
            const box = document.createElement("div");
            box.classList.add("dragdrop-container");

            if (elemento.titulo) {
                const titulo = document.createElement("h3");
                titulo.textContent = elemento.titulo;
                box.appendChild(titulo);
            }

            // área com os itens ainda não classificados
            const areaItens = document.createElement("div");
            areaItens.classList.add("dragdrop-itens");

            // itens embaralhados
            const itens = [...elemento.itens].sort(() => Math.random() - 0.5);

            itens.forEach(item => {
                const chip = document.createElement("div");
                chip.textContent = item.texto;
                chip.classList.add("dragdrop-item");
                chip.draggable = true;
                chip.dataset.categoria = item.categoria;

                chip.addEventListener("dragstart", e => {
                    e.dataTransfer.setData("text/plain", item.texto);
                });

                areaItens.appendChild(chip);
            });

            box.appendChild(areaItens);

            // colunas de categorias
            const colunas = document.createElement("div");
            colunas.classList.add("dragdrop-colunas");

            elemento.categorias.forEach(categoria => {
                const coluna = document.createElement("div");
                coluna.classList.add("dragdrop-coluna");
                coluna.dataset.categoria = categoria;

                const tituloColuna = document.createElement("h4");
                tituloColuna.textContent = categoria;
                coluna.appendChild(tituloColuna);

                coluna.addEventListener("dragover", e => e.preventDefault());

                coluna.addEventListener("drop", e => {
                    e.preventDefault();
                    const texto = e.dataTransfer.getData("text/plain");
                    const chip = [...areaItens.querySelectorAll(".dragdrop-item")]
                        .find(c => c.textContent === texto);

                    if (!chip) return;

                    if (chip.dataset.categoria === categoria) {
                        chip.classList.add("correta");
                    } else {
                        chip.classList.add("errada");
                    }
                    chip.draggable = false;
                    coluna.appendChild(chip);
                });

                colunas.appendChild(coluna);
            });

            box.appendChild(colunas);
            return box;
        }

        // ---------- quiz final (sequência de perguntas) ----------
        function criarQuizFinal(questoes) {

            let indice = 0;
            let pontos = 0;

            const quiz = document.createElement("div");
            quiz.classList.add("quiz-container");

            function carregarQuestao() {
                quiz.innerHTML = "";
                const questao = questoes[indice];

                const pergunta = document.createElement("h2");
                pergunta.textContent = questao.pergunta;
                quiz.appendChild(pergunta);

                questao.alternativas.forEach((alt, i) => {
                    const botao = document.createElement("button");
                    botao.textContent = alt;
                    botao.classList.add("alternativa");

                    botao.onclick = () => {
                        if (i === questao.correta) {
                            pontos++;
                            botao.classList.add("correta");
                        } else {
                            botao.classList.add("errada");
                        }

                        const botoes = quiz.querySelectorAll("button");
                        botoes.forEach(b => b.disabled = true);

                        setTimeout(() => {
                            indice++;
                            if (indice < questoes.length) {
                                carregarQuestao();
                            } else {
                                finalizarQuiz();
                            }
                        }, 800);
                    };
                    quiz.appendChild(botao);
                });

                const contador = document.createElement("p");
                contador.textContent = `Questão ${indice + 1}/${questoes.length}`;
                quiz.appendChild(contador);
            }

            function finalizarQuiz() {
                quiz.innerHTML = "";
                const resultado = document.createElement("h2");
                resultado.textContent = `Você acertou ${pontos}/${questoes.length}!`;
                quiz.appendChild(resultado);

                if (pontos >= 4) {
                    localStorage.setItem(`nivel${numeroNivel}`, "concluido");

                    const msg = document.createElement("p");
                    msg.textContent = " Nível concluído! Próximo nível desbloqueado.";
                    quiz.appendChild(msg);
                } else {
                    const msg = document.createElement("p");
                    msg.textContent = " Você precisa revisar o conteúdo e tentar novamente.";
                    quiz.appendChild(msg);
                }
            }

            carregarQuestao();
            return quiz;
        }

        // ---------- montagem das seções ----------
        nivel.secoes.forEach(secao => {
            const section = document.createElement("section");

            if (secao.classe)
                section.classList.add(secao.classe);

            secao.elementos.forEach(elemento => {
                section.appendChild(criarElemento(elemento));
            });

            container.appendChild(section);
        });
    })
    .catch(err => {
        console.error("Erro ao carregar o nível:", err);
        const container = document.getElementById("conteudo");
        if (container) {
            container.textContent = "Não foi possível carregar este nível.";
        }
    });