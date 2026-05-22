const olhos = document.querySelectorAll(".toggleSenha");

olhos.forEach((olho) => {
    olho.addEventListener("click", () => {

        const inputbox = olho.closest(".inputbox");
        const inputSenha = inputbox.querySelector("input");

        if (inputSenha.type === "password") {
            inputSenha.type = "text";
            olho.setAttribute("name", "eye-outline");
        } else {
            inputSenha.type = "password";
            olho.setAttribute("name", "eye-off-outline");
        }
    });
});