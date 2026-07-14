function renderCards(secao,container){

    const section=document.createElement("section");

    section.innerHTML=`<h2>${secao.titulo}</h2>`;

    const grid=document.createElement("div");

    grid.className="grid-cards";

    secao.cards.forEach(card=>{

        const div=document.createElement("div");

        div.className="card";

        div.innerHTML=`

            <h3>${card.titulo}</h3>

            <p>${card.texto}</p>

        `;

        grid.appendChild(div);

    });

    section.appendChild(grid);

    container.appendChild(section);

}