package com.example.demo.model;

// Um "enum" é uma lista fechada de valores possíveis. Em vez de aceitar
// qualquer String solta (o que deixaria fácil digitar errado, tipo "Texto"
// vs "TEXTO" vs "texto"), a gente trava as opções aqui.
// Isso vai virar o "tipo" de cada tela da lição.
public enum StepType {
    ABERTURA,          // tela inicial da lição: título, imagem, tempo estimado e XP prometido
    CONTEUDO,          // bloco de texto explicativo, com imagem e um box de dica/resumo
    COMPARACAO,        // duas colunas comparando dois conceitos
    VIDEO,             // vídeo embutido (Momento cinema)
    PARABENS,          // tela de transição antes do quiz ("agora vamos testar seus conhecimentos")
    QUIZ,              // pergunta de múltipla escolha simples
    JOGO,              // mini-jogo — o campo "subtipo" dentro do JSON diz qual (ordenar-sequencia,
                       // classificar-2-categorias, classificar-3-categorias, cenario-multipla-escolha,
                       // cenario-escolha, identificar-em-lista...)
    RESUMO,            // tela de fim de LIÇÃO, com o XP total ganho
    CONCLUSAO_TRILHA,  // tela de fim de NÍVEL inteiro (todas as lições concluídas)
    CONCLUSAO_CURSO    // tela de fim do CURSO inteiro (todos os níveis concluídos)
}