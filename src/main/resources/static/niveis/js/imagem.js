function renderImagem(secao,container){

    const section=document.createElement("section");

    section.className="imagem";

    section.innerHTML=`

        <h2>${secao.titulo}</h2>

        <img src="${secao.imagem}" alt="${secao.titulo}">

        <p>${secao.legenda}</p>

    `;

    container.appendChild(section);

}