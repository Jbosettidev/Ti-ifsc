function renderNivel(nivel, container){

    nivel.secoes.forEach(secao=>{

        switch(secao.tipo){

            case "hero":
                renderHero(secao,container);
                break;

            case "texto":
                renderTexto(secao,container);
                break;

            case "imagem":
                renderImagem(secao,container);
                break;

            case "cards":
                renderCards(secao,container);
                break;

            case "quiz":
                renderQuiz(secao,container);
                break;

            case "video":
                renderVideo(secao,container);
                break;

            case "curiosidade":
                renderCuriosidade(secao,container);
                break;

            case "conclusao":
                renderConclusao(secao,container);
                break;

            default:
                console.log("Tipo desconhecido:",secao.tipo);

        }

    });

}