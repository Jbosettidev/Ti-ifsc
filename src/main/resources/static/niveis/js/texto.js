function renderTexto(secao,container){

    const section=document.createElement("section");

    section.className="texto";

    section.innerHTML=`

        <h2>${secao.titulo}</h2>

        <p>${secao.texto}</p>

    `;

    container.appendChild(section);

}