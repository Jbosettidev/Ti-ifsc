const svg = document.querySelector(".linhas"); //pega o svg la no html
const botoes = document.querySelectorAll(".botao-geral"); //pega os botao tudo

function desenharLinhas() {

    //limpa as linhas; IMPORTANTE pra nao duplicar tudo no resizer
    svg.innerHTML = "";

    //pega posicao e tamanho do container
    const containerRect =
        document.querySelector(".container")
        .getBoundingClientRect();


    //vai percorrer todos os botoes menos o ultimo (pro ultimo naot ter linha saindo dele)
    for(let i = 0; i < botoes.length - 1; i++) {

        //botao atual e próximo, respectivamente
        const atual = botoes[i];
        const prox = botoes[i + 1];

        //pega posicao do atual e proximo, respectivamente
        const r1 = atual.getBoundingClientRect();
        const r2 = prox.getBoundingClientRect();

        //calcula o centro X e Y do botao atual e próximo
        const x1 =
            r1.left + r1.width / 2 - containerRect.left;

        const y1 =
            r1.top + r1.height / 2 - containerRect.top;

        const x2 =
            r2.left + r2.width / 2 - containerRect.left;

        const y2 =
            r2.top + r2.height / 2 - containerRect.top;

        // controla o quanto curva
        const curva = 120;

        //cria um elemento do tipo path (linha/caminho/curva)
        const path = document.createElementNS(
            "http://www.w3.org/2000/svg",
            "path"
        );

        //cria o caminho
        const d = `
            M ${x1} ${y1}
            C ${x1} ${y1 + curva},
              ${x2} ${y2 - curva},
              ${x2} ${y2}
        `;

        //aplica o caminho
        path.setAttribute("d", d);

        //sem preenchimento
        path.setAttribute("fill", "none");

        //cor 
        path.setAttribute("stroke", "#8f7cff");

        //grossura
        path.setAttribute("stroke-width", "6");

        //pique um border radius
        path.setAttribute("stroke-linecap", "round");

        //adiciona a linha
        svg.appendChild(path);
    }
}

//desenha as linhas quandio a pagina abre
desenharLinhas();

//refaz tudo se a tela mudar de tamanho
window.addEventListener("resize", desenharLinhas);