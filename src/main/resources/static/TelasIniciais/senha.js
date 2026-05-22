const senha = document.getElementById("senha"); //pega o input

const toggle = //pega o icone do olho
    document.getElementById("toggleSenha");

toggle.addEventListener("click", () => { //faz ele mudar quando a pessoa clica no olho

    if(senha.type === "password") {

        senha.type = "text"; //faz virar texto

        toggle.setAttribute(
            "name",
            "eye-outline" //muda o icone 
        );

    } else {

        senha.type = "password";

        toggle.setAttribute(
            "name",
            "eye-off-outline"
        );

    }

});