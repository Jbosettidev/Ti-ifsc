function renderCuriosidade(secao,container){

    const section=document.createElement("section");

    section.className="curiosidade";

    section.innerHTML=`

        <h2>💡 Curiosidade</h2>

        <p>${secao.texto}</p>

    `;

    container.appendChild(section);

}