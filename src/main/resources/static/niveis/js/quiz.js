function renderQuiz(secao,container){

    const section=document.createElement("section");

    section.className="quiz";

    const pergunta=document.createElement("h2");

    pergunta.textContent=secao.pergunta;

    section.appendChild(pergunta);

    const feedback=document.createElement("p");

    feedback.className="feedback";

    secao.alternativas.forEach((alternativa,index)=>{

        const button=document.createElement("button");

        button.textContent=alternativa;

        button.onclick=()=>{

            if(index===secao.correta){

                feedback.textContent="✅ Correto!";

                feedback.style.color="green";

            }

            else{

                feedback.textContent="❌ Tente novamente.";

                feedback.style.color="red";

            }

        };

        section.appendChild(button);

    });

    section.appendChild(feedback);

    container.appendChild(section);

}