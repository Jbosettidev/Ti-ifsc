package com.example.demo.model;

// O "nome do arquivo" da imagem também mora aqui, pra o frontend
// saber qual ícone mostrar sem precisar de mais lógica.
//tem que ver pra por o icon pra cada tipo de medalha
public enum Medalha {

    // Diamante
    FINALIZAR_CURSO("medalha-finalizar-curso"),
    TERMINAR_NIVEL_100("medalha-terminar-nivel"),

    // Ouro
    OFENSIVA_30_DIAS("medalha-ofensiva-ouro"),
    JOGO_FINAL_PONTUACAO_MAXIMA("medalha-jogofinal-ouro"),

    // Prata
    OFENSIVA_15_DIAS("medalha-ofensiva-prata"),
    TERMINAR_NIVEL_80("medalha-terminar-nivel80"),

    // Cobre
    OFENSIVA_7_DIAS("medalha-ofensiva-cobre"),
    PRIMEIRA_MISSAO("medalha-pmissao-cobre");

    private final String nomeArquivoIcone;

    Medalha(String nomeArquivoIcone) {
        this.nomeArquivoIcone = nomeArquivoIcone;
    }

    public String getNomeArquivoIcone() {
        return nomeArquivoIcone;
    }
}