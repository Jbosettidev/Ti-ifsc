function renderVideo(secao,container){

    const section=document.createElement("section");

    section.className="video";

    section.innerHTML=`

        <h2>${secao.titulo}</h2>

        <iframe
            src="${secao.url}"
            width="100%"
            height="400"
            allowfullscreen>
        </iframe>

    `;

    container.appendChild(section);

}