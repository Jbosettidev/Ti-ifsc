fetch("niveis.json") //fetch puxa o arquivo JSON

    .then(res => res.json()) //res vai transformar esse json em um elemento JS

    .then(dados => {

        const params =
            new URLSearchParams(window.location.search);

        const numeroNivel =
            params.get("nivel");

        const nivel =
            dados[numeroNivel];
        const container =
            document.getElementById("conteudo"); //pega a div no html

        nivel.secoes.forEach(secao => { //loop, vai pegar todas as seções dentro do JSON

            const section =
                document.createElement("section"); //para cada seção, ele cria uma section no html

            section.classList.add(secao.classe);
            section.innerHTML = `
                <h1>${secao.titulo}</h1> 
                <p>${secao.texto}</p>   
            `; //cria um titulo com o elemento titulo e um parágrafo com o parágrafo (secao.nome pra pegar o elemento dentro da lista)

            container.appendChild(section); //coloca as sections dentro do container

        });

    });