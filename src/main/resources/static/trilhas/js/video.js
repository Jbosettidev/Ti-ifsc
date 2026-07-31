// video.js - renderiza a seção de vídeo.
// Como as missões ainda não têm links verificados, mostra um cartão de
// "vídeo a definir" com a sugestão de busca, e só troca para um embed real
// quando a seção já tiver o campo "url" preenchido.
function renderVideo(secao) {
    const section = document.createElement("section");
    section.className = "secao-video";

    if (secao.titulo) {
        const h2 = document.createElement("h2");
        h2.textContent = secao.titulo;
        section.appendChild(h2);
    }

    if (secao.url) {
        const iframe = document.createElement("iframe");
        iframe.className = "video-embed";
        iframe.src = secao.url;
        iframe.setAttribute("allowfullscreen", "");
        iframe.setAttribute("loading", "lazy");
        section.appendChild(iframe);
    } else {
        const aviso = document.createElement("div");
        aviso.className = "video-pendente";
        const linha1 = document.createElement("p");
        linha1.textContent = "\uD83C\uDFA5 Vídeo ainda não definido.";
        const linha2 = document.createElement("p");
        linha2.className = "video-sugestao";
        linha2.textContent = "Sugestão de busca: \"" + (secao.sugestaoBusca || "") + "\"";
        aviso.appendChild(linha1);
        aviso.appendChild(linha2);
        section.appendChild(aviso);
    }

    return section;
}

window.Renderers = window.Renderers || {};
window.Renderers.video = renderVideo;
