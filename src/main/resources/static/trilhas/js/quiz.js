// quiz.js - renderiza uma ou mais perguntas de múltipla escolha
function renderQuiz(secao) {
    const section = document.createElement("section");
    section.className = "secao-quiz";

    (secao.perguntas || []).forEach((pergunta) => {
        const bloco = document.createElement("div");
        bloco.className = "quiz-pergunta";

        const h3 = document.createElement("h3");
        h3.textContent = pergunta.pergunta;
        bloco.appendChild(h3);

        const opcoesDiv = document.createElement("div");
        opcoesDiv.className = "quiz-opcoes";

        const feedback = document.createElement("p");
        feedback.className = "quiz-feedback";

        pergunta.opcoes.forEach((opcaoTexto, indice) => {
            const btn = document.createElement("button");
            btn.type = "button";
            btn.className = "quiz-opcao";
            btn.textContent = opcaoTexto;

            btn.addEventListener("click", () => {
                if (bloco.classList.contains("respondida")) return;
                bloco.classList.add("respondida");

                const correta = indice === pergunta.respostaCorreta;
                btn.classList.add(correta ? "correta" : "incorreta");

                if (!correta) {
                    const botaoCorreto = opcoesDiv.children[pergunta.respostaCorreta];
                    if (botaoCorreto) botaoCorreto.classList.add("correta");
                }

                feedback.textContent = pergunta.explicacao || (correta ? "Correto!" : "Não foi dessa vez.");
                feedback.classList.add(correta ? "ok" : "erro");

                Array.from(opcoesDiv.children).forEach((b) => (b.disabled = true));
            });

            opcoesDiv.appendChild(btn);
        });

        bloco.appendChild(opcoesDiv);
        bloco.appendChild(feedback);
        section.appendChild(bloco);
    });

    return section;
}

window.Renderers = window.Renderers || {};
window.Renderers.quiz = renderQuiz;
