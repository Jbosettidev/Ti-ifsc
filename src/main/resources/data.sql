--=========================================================================
-- NIVEL 1
INSERT INTO level (id, titulo, descricao, ordem)
VALUES (1, 'Nível 1', 'Domine os primeiros passos da segurança digital!', 1);

--==========================================================================
--LICAO 1.1

INSERT INTO lesson (id, titulo, ordem, xp_total, desafio_final, level_id)
VALUES (1, 'Por que a informação vale tanto?', 1, 60, false, 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "Por que a informação vale tanto?",
  "texto": "Toda jornada de cibersegurança começa com uma pergunta simples: por que alguém se daria ao trabalho de te atacar?",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "3-4 min",
  "XP": "+60 XP"
}', 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O ciberespaço",
  "paragrafos": [
    "Vivemos conectados por um espaço que não existia há 40 anos: o ciberespaço, a rede de computadores, celulares e sistemas conversando entre si o tempo todo.",
    "Nesse espaço, a informação virou um dos bens mais valiosos que existem. E diferente de um carro ou uma casa, ela não se desgasta com o uso."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "Você sabia?",
  "dica": "Uma foto, uma senha ou um documento podem ser copiados infinitas vezes e o original continua intacto."
}', 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'COMPARACAO',
'{
  "titulo": "Bem Físico X Informação Digital",
  "imagem": "byte-comparativo.svg",
  "colunaA": { "titulo": "Bem físico", "texto": "Se alguém rouba seu celular, você percebe na hora." },
  "colunaB": { "titulo": "Informação Digital", "texto": "Se alguém copia seus dados, o original continua com você, você pode nem saber que foi copiado." }
}', 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'VIDEO',
'{
  "titulo": "Momento cinema",
  "imagem": "byte-momento-cinema.svg",
  "urlVideo": "https://youtu.be/uY3fUAEqyuA?si=Os1Zz6GW1m6xSALg"
}', 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 1);


INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "O que torna a informação diferente de um bem físico como um carro?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Ela pode ser copiada sem se desgastar",
    "Ela perde valor ao longo do tempo",
    "Ela não pode ser roubada"
  ],
  "correta": 0,
  "xp": 30,
  "explicacao": "Informação pode ser copiada infinitas vezes sem se desgastar, diferente de um bem físico."
}', 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'QUIZ',
'{
  "pergunta": "Por que às vezes nem percebemos que fomos roubados digitalmente?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Porque hackers sempre avisam a vítima",
    "Porque o original continua com a gente mesmo depois da cópia",
    "Porque dados digitais não podem ser copiados"
  ],
  "correta": 1,
  "xp": 30,
  "explicacao": "Diferente de um objeto físico, copiar um dado não faz o original sumir, por isso o roubo pode passar despercebido."
}', 1);


INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 1);


--============================================================================================
--LICAO 1.2
INSERT INTO lesson (id, titulo, ordem, xp_total, desafio_final, level_id)
VALUES (2, 'O que é cibersegurança, de verdade?', 2, 60, false, 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "O que estamos protegendo?",
  "texto": "Na missão passada você viu por que a informação vale tanto. Agora: o que exatamente estamos protegendo quando falamos em cibersegurança?",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "6 min",
  "XP": "+60 XP"
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "Definição",
  "paragrafos": [
    "Cibersegurança é o conjunto de práticas para proteger sistemas, redes e dados contra ataques digitais. Ela existe pra garantir 3 coisas ao mesmo tempo e se uma delas falha, algo deu errado.",
    "Confidencialidade: significa que só quem deveria ver uma informação, vê. Se alguém lê suas mensagens privadas sem permissão, a confidencialidade foi quebrada, mesmo que nada tenha sido apagado ou mudado.",
    "Integridade: significa que a informação continua exatamente como deveria estar, sem alterações indevidas. Se alguém invade sua conta e muda sua nota de uma prova online, a integridade foi quebrada.",
    "Disponibilidade: significa que você consegue acessar seus dados e sistemas quando precisa. Se um ataque derruba o site da sua escola bem na hora da matrícula, a disponibilidade foi quebrada."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "Resumindo",
  "dica": "Na confidencialidade só quem deve ver, vê. Na Integridade: A informação continua correta e intacta e na disponibilidade você consegue acessar quando precisa."
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'COMPARACAO',
'{
  "titulo": "Cibersegurança x Segurança da Informação",
  "imagem": "byte-comparativo.svg",
  "colunaA": { "titulo": "Cibersegurança", "texto": "Foca no mundo digital e conectado à internet" },
  "colunaB": { "titulo": "Segurança da Informação", "texto": "É mais ampla e protege dados em qualquer formato, até um documento de papel trancado numa gaveta." }
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'VIDEO',
'{
  "titulo": "A tríade CID explicada",
  "imagem": "byte-momento-cinema.svg",
  "urlVideo": "https://youtu.be/u_ehqU6bs8s?si=A8kVDjTgK5JPTkD7"
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "Alguém lê suas mensagens privadas sem sua permissão. Qual pilar foi violado?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Confidencialidade",
    "Integridade",
    "Disponibilidade"
  ],
  "correta": 0,
  "xp": 20,
  "explicacao": "Acesso não autorizado a informação privada é uma violação de confidencialidade."
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'QUIZ',
'{
  "pergunta": "Um ataque derruba o site de matrícula bem no dia da inscrição. Qual pilar foi violado?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
     "Confidencialidade",
     "Integridade",
     "Disponibilidade"
  ],
  "correta": 2,
  "xp": 20,
  "explicacao": "Um ataque que derruba o acesso ao sistema é uma violação de disponibilidade."
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'QUIZ',
'{
  "pergunta": "Um hacker invade o sistema da escola e muda notas de alunos. Qual pilar foi violado?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
     "Confidencialidade",
     "Integridade",
     "Disponibilidade"
  ],
  "correta": 1,
  "xp": 20,
  "explicacao": "Alterar dados indevidamente é uma violação de integridade."
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (9, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 2);

--==============================================================
--Lição 3
INSERT INTO lesson (id, titulo, ordem, xp_total, desafio_final, level_id)
VALUES (3, 'Sua pegada digital', 3, 60, false, 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "Você tem identidade digital?",
  "texto": "Você já se perguntou se tem uma identidade digital? Se você usa a internet, mesmo sem Instagram ou TikTok, a resposta é sim.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "2 min",
  "XP": "+60 XP"
}', 3);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O mito",
  "paragrafos": [
    "Muita gente acha que só tem identidade digital quem usa redes sociais. Não é verdade. Toda vez que você acessa a internet você deixa rastros mesmo sem postar nada.",
    "O que é um rastro digital? São os sites que você visita, buscas que você faz, o tempo que você fica em cada página: tudo isso pode ser coletado, guardado e analisado, mesmo que você nunca tenha criado uma conta em lugar nenhum."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "Pra que servem esses rastros?",
  "dica": "Esses rastros têm valor. Empresas os usam para te mostrar anúncios mais certeiros e é por isso que, às vezes, parece que o celular está escutando você. Mas em muitos casos, é só o histórico de navegação sendo usado."
}', 3);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'COMPARACAO',
'{
  "titulo": "Quem se interessa pelos seus dados",
  "imagem": "byte-comparativo.svg",
  "colunaA": { "titulo": "Criminosos", "texto": "Podem usar seus rastros para roubo de identidade ou golpes personalizados." },
  "colunaB": { "titulo": "Empresas", "texto": "Coletam dados de navegação para anúncios direcionados, nem sempre é golpe, mas também merece atenção." }
}', 3);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 3);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "Uma pessoa nunca usou redes sociais. Ela tem identidade digital?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Não, só se você possuir uma conta nas redes sociais seus dados são salvos",
    "Só se ela tiver email",
    "Sim, toda navegação deixa rastros"
  ],
  "correta": 2,
  "xp": 30,
  "explicacao": "Qualquer uso da internet, mesmo sem redes sociais, gera rastros digitais."
}', 3);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "Por que empresas se interessam pelos seus rastros de navegação?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
     "Para vender seus dados",
     "Elas não têm interesse",
     "Para personalizar anúncios com base no seu comportamento"
  ],
  "correta": 2,
  "xp": 30,
  "explicacao": "O uso mais comum é personalização de anúncios, embora dados também possam vazar ou ser mal utilizados."
}', 3);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 3);

--==========================================
--Lição 4
INSERT INTO lesson (id, titulo, ordem, xp_total, desafio_final, level_id)
VALUES (4, 'Quem são os invasores?', 4, 60, false, 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "Quem está do outro lado?",
  "texto": "Última missão desta trilha! Você já sabe o que estamos protegendo (CID) e por que seus dados valem tanto. Agora: quem, exatamente, está tentando pegar essas informações?",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "8 min",
  "XP": "+60 XP"
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "Nem todo invasor é igual",
  "paragrafos": [
    "A palavra hacker costuma vir com uma imagem só na cabeça: alguém de capuz digitando rápido no escuro. Mas na prática, existem vários tipos, com motivações bem diferentes entre si.",
    "Script kiddies: São invasores iniciantes que usam ferramentas prontas, feitas por outras pessoas, sem entender muito bem como funcionam por dentro. Mesmo amadores, os estragos que causam podem ser reais.",
    "White hat: Invadem sistemas de propósito, mas com autorização, pra encontrar falhas antes que criminosos as encontrem. No fim, reportam tudo pro dono do sistema.",
    "Black hat: Exploram qualquer falha que encontrarem pra ganho próprio: dinheiro, vantagem pessoal ou política. Não têm autorização nenhuma e não reportam nada, é o oposto do white hat.",
    "Hackers organizados: Grupos mais sofisticados: hacktivistas (que atacam por causas políticas), criminosos que vendem crime digital como serviço, e até invasores financiados por governos, com treinamento e recursos bem maiores que um invasor comum."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "E o grey hat?",
  "dica": "Existe também o grey hat: alguém que encontra falhas sem autorização, mas só reporta se for do interesse dele, às vezes até publica a falha na internet, o que pode ajudar outros criminosos a explorá-la."
}', 4);


INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'COMPARACAO',
'{
  "titulo": "Resumindo (parte 1)",
  "imagem": "byte-comparativo.svg",
  "colunaA": { "titulo": "Script kiddie", "texto": "Usa ferramenta pronta, pouca experiência." },
  "colunaB": { "titulo": "White hat", "texto": "Invade com autorização pra ajudar." }
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'COMPARACAO',
'{
  "titulo": "Resumindo (parte 2)",
  "imagem": "byte-comparativo.svg",
  "colunaA": { "titulo": "Black hat", "texto": "Invade sem autorização, pra ganho próprio." },
  "colunaB": { "titulo": "Hacker organizado", "texto": "Grupo com recursos e treinamento, motivação política ou financeira em larga escala." }
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'VIDEO',
'{
  "titulo": "A tríade CID explicada",
  "imagem": "byte-momento-cinema.svg",
  "urlVideo": "https://youtu.be/RF2ZVqxM1ZI?si=uLXC-9dTmXD9zt2i"
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 4);


INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'QUIZ',
'{
  "pergunta": "Um profissional é contratado para invadir um sistema de propósito e reportar as falhas encontradas. Que tipo de invasor é esse?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "White Hat",
    "Black Hat",
    "Script Kiddie"
  ],
  "correta": 0,
  "xp": 20,
  "explicacao": "White hat invade com autorização para melhorar a segurança."
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'QUIZ',
'{
  "pergunta": "Alguém usa ferramentas prontas da internet, sem entender bem como funcionam, para causar dano. Que tipo de invasor é esse?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
     "Hacker Organizado",
     "White Hat",
     "Script kiddie"
  ],
  "correta": 2,
  "xp": 20,
  "explicacao": "Script kiddie usa ferramentas de terceiros sem grande domínio técnico."
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (9, 'QUIZ',
'{
  "pergunta": "Um grupo bem treinado ataca a rede elétrica de uma cidade a mando de um governo. Que tipo de invasor é esse?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
     "Hacker Organizado",
     "White Hat",
     "Script kiddie"
  ],
  "correta": 0,
  "xp": 20,
  "explicacao": "Ataques de grande escala com recursos e motivação política/governamental são típicos de hackers organizados."
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (10, 'RESUMO',
'{
  "titulo": "PARABÉNS!!!!! Você terminou o nível 1",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 4);


--=========================================================================
-- NIVEL 2
INSERT INTO level (titulo, descricao, ordem)
VALUES ('Nível 2 ', 'Conheça os principais malwares e como eles agem!', 2);

--==========================================================================
--LICAO 2.1
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Phishing: a isca no anzol', 1, 60, false, 2)

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "Trilha nova: Malware",
  "texto": "Agora que você entende os fundamentos, vamos conhecer as ameaças mais comuns na prática. Começando pela mais antiga de todas: o phishing.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "4-5 min",
  "XP": "+60 XP"
}', 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O que é",
  "paragrafos": [
    "Phishing é quando criminosos se passam por empresas ou pessoas confiáveis por e-mail, SMS ou redes sociais pra te convencer a entregar senhas, dados de cartão ou outras informações sigilosas.",
    "Phishing não explora uma falha no seu celular ou computador. Ele explora você, sua confiança, sua pressa ou seu medo. É por isso que até gente experiente pode cair."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "Engenheiros sociais profissionais",
  "dica": "Existem golpistas profissionais especializados só em manipulação psicológica, chamados de engenheiros sociais. Eles são tão bons no que fazem que às vezes até um profissional de segurança tem dificuldade de perceber o golpe."
}', 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'COMPARACAO',
'{
  "titulo": "Sinais de alerta",
  "imagem": "byte-comparativo.svg",
  "colunaA": {
    "titulo": "Urgência",
    "texto": "''Sua conta será bloqueada em 24h!''"
  },
  "colunaB": {
    "titulo": "Erros de escrita",
    "texto": "Erros de português ou gramática na mensagem."
  },
  "colunaC": {
    "titulo": "Remetente estranho",
    "texto": "Link ou remetente que parece estranho, mesmo que familiar."
  }
}', 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'VIDEO',
'{
  "titulo": "Como identificar phishing",
  "imagem": "byte-momento-cinema.svg",
  "urlVideo": "https://youtu.be/HIWm1oxhQYk?si=zGi-NhzWSqO73mLW"
}', 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "Phishing tem sucesso principalmente porque explora:",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Uma falha técnica no seu celular",
    "A confiança, pressa ou medo da vítima",
    "Vírus instalados no dispositivo"
  ],
  "correta": 1,
  "xp": 20,
  "explicacao": "Phishing é engenharia social: explora a vulnerabilidade humana, não uma falha técnica."
}', 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'QUIZ',
'{
  "pergunta": "Qual desses é um sinal de alerta comum em phishing?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "A mensagem demora dias pra chegar",
    "A mensagem cria urgência exagerada (''aja agora ou perca sua conta'')",
    "A mensagem vem sem nenhum link"
  ],
  "correta": 1,
  "xp": 20,
  "explicacao": "Urgência exagerada é uma das táticas mais comuns para forçar decisões precipitadas."
}', 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'JOGO',
'{
  "subtipo": "simulacao-email",
  "instrucao": "Leia o e-mail e decida: manter ou deletar?",
  "cenarios": [
    {
      "remetente": "bancocentralooficial123@gmail.com",
      "assunto": "Bloqueio de sua conta ocorrerá em 24 Horas!",
      "corpo": "Prezado cliente, identificamos atividades suspeitas em sua conta. Clique no link abaixo para regularizar sua situação antes do bloqueio.",
      "ehFraude": true,
      "xp": 20,
      "sinaisPresentes": [
        "Urgência",
        "Remetente estranho"
      ],
      "feedbackCorreto": "Correto! Esse e-mail é fraudulento — remetente estranho e urgência exagerada.",
      "feedbackIncorreto": "Esse e-mail é fraudulento. Repare no remetente (um Gmail comum, não um banco oficial) e na urgência criada."
    }
  ]
}', 1);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (9, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: ",
}', 1);


--==========================================================================
--LICAO 2.2
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Scareware: o susto falso', 2, 30, false, 2)

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "SEU COMPUTADOR ESTÁ INFECTADO!",
  "texto": "Quem nunca viu um pop-up assim? Nessa missão você aprende por que ele quase sempre é o golpe, não o aviso.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "3-4 min",
  "XP": "+60 XP"
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O que é",
  "paragrafos": [
    "Scareware é um falso aviso de segurança, geralmente um ''antivírus'' que na verdade não existe, feito pra te assustar e fazer você agir sem pensar.",
    "Ele costuma vir como um pop-up alarmante, com botões como ''Remover agora'' ou ''Resolver problema''. Clicar neles pode instalar um malware de verdade ou te levar pra um site malicioso."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "O perigo real está no clique",
  "dica": "O scareware em si não protege nem ataca tecnicamente seu dispositivo, o perigo real está no clique. Ou seja, o ''vírus'' só vira ameaça de verdade se você interagir com o pop-up falso."
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'COMPARACAO',
'{
  "titulo": "O que fazer",
  "imagem": "byte-comparativo.svg",
  "colunaA": {
    "titulo": "Faça",
    "texto": "Feche a aba do navegador inteira, sem clicar em nenhum botão do pop-up."
  },
  "colunaB": {
    "titulo": "Não faça",
    "texto": "Nunca clique em ''Remover'' ou ''Resolver'' dentro do próprio pop-up suspeito."
  }
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "Um pop-up avisa ''SEU CELULAR ESTÁ INFECTADO, CLIQUE AQUI PARA LIMPAR''. O que é isso, provavelmente?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Scareware",
    "Um antivírus real fazendo o trabalho dele",
    "Uma atualização do sistema"
  ],
  "xp": 20,
  "correta": 0,
  "explicacao": "Esse é o padrão clássico de scareware: alerta alarmista com botão de ação imediata."
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "Qual é a atitude mais segura ao ver esse tipo de pop-up?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Clicar em ''Remover agora'' pra resolver rápido",
    "Fechar a aba do navegador sem clicar em nada dentro do pop-up",
    "Ligar para o número de telefone que aparece na tela"
  ],
  "xp": 20,
  "correta": 1,
  "explicacao": "Fechar a aba sem interagir com o pop-up evita ativar qualquer ação maliciosa embutida nele."
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'JOGO',
'{
  "subtipo": "classificar-2-categorias",
  "instrucao": "Isso é um aviso real do sistema ou scareware?",
  "xp": 20,
  "categorias": [
    "Aviso real",
    "Scareware"
  ],
  "itens": [
    {
      "texto": "Notificação nativa: ''Atualização de segurança disponível''",
      "categoria": "Aviso real"
    },
    {
      "texto": "Pop-up vermelho piscando: ''VOCÊ TEM 3 VÍRUS! CLIQUE AQUI''",
      "categoria": "Scareware"
    },
    {
      "texto": "Aviso do navegador: ''Este site não é seguro (sem HTTPS)''",
      "categoria": "Aviso real"
    },
    {
      "texto": "Pop-up com contagem regressiva: ''Seu dispositivo será bloqueado em 60 segundos!''",
      "categoria": "Scareware"
    }
  ]
}', 2);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (9, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 2);


--==========================================================================
--LICAO 2.3
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Worms: a praga que se espalha sozinha', 3, 60, false, 2)

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "Uma praga que se espalha sozinha",
  "texto": "Alguns malwares precisam que você clique em algo. Outros não, eles se espalham sozinhos, como uma praga. Conheça o worm.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "3-4 min",
  "XP": "+60 XP"
}', 3);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O que é",
  "paragrafos": [
    "Worm é um malware independente que se copia e se espalha entre dispositivos sozinho, sem precisar de nenhuma ação sua depois da infecção inicial.",
    "Ele costuma entrar por anexos de e-mail, pen drives infectados ou falhas de segurança no sistema. Uma vez dentro, ele se replica sem você perceber, podendo apagar arquivos ou instalar outros malwares."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "Worm x vírus tradicional",
  "dica": "Diferente de um vírus tradicional, que precisa ''grudar'' em um arquivo pra existir, o worm é independente — ele não precisa de nenhum programa hospedeiro pra se espalhar."
}', 3);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'VIDEO',
'{
  "titulo": "O que é um worm",
  "imagem": "byte-momento-cinema.svg",
  "urlVideo": "https://youtu.be/-jvTzqD07V8?si=TkVPKESbiP97wlV_"
}', 3);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 3);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "O que torna um worm diferente de outros malwares?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Ele se replica e se espalha sozinho, sem ação do usuário",
    "Ele só funciona se o usuário clicar nele toda vez",
    "Ele nunca causa dano real"
  ],
  "correta": 0,
  "xp": 20,
  "explicacao": "A autorreplicação sem ação contínua do usuário é a característica central do worm."
}', 3);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "Qual desses é uma forma comum de um worm entrar no seu dispositivo?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Ligar o wifi",
    "Abrir um anexo de e-mail infectado",
    "Desligar o celular"
  ],
  "correta": 1,
  "xp": 20,
  "explicacao": "Anexos de e-mail infectados são uma via clássica de propagação de worms."
}', 3);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'JOGO',
'{
  "subtipo": "classificar-2-categorias",
  "instrucao": "Isso pode espalhar um worm?",
  "xp": 20,
  "categorias": [
    "Pode espalhar",
    "Seguro"
  ],
  "itens": [
    {
      "texto": "Abrir anexo de e-mail desconhecido",
      "categoria": "Pode espalhar"
    },
    {
      "texto": "Conectar um pen drive de origem desconhecida",
      "categoria": "Pode espalhar"
    },
    {
      "texto": "Manter o sistema atualizado",
      "categoria": "Seguro"
    },
    {
      "texto": "Baixar app só de loja oficial",
      "categoria": "Seguro"
    }
  ]
}', 3);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 3);


--==========================================================================
--LICAO 2.4
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Spyware: o espião silencioso', 4, 60, false, 2)

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "O espião silencioso",
  "texto": "Nem todo malware quer destruir seus arquivos. Alguns só querem observar, em silêncio.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "4-5 min",
  "XP": "+60 XP"
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O que é",
  "paragrafos": [
    "Spyware é um programa que espiona o que você faz no dispositivo e envia essas informações pra outra pessoa, sem que você perceba.",
    "Keylogger: Registra tudo o que você digita, inclusive senhas. Se você digita a senha do banco, ele salva exatamente o que foi digitado.",
    "Screenlogger: Tira fotos ou grava sua tela enquanto você usa o dispositivo, por exemplo, quando você abre o app do banco.",
    "Adware: Coleta dados sobre sua navegação pra te mostrar anúncios personalizados. É o menos perigoso dos três, mas ainda é uma invasão de privacidade."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg"
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'COMPARACAO',
'{
  "titulo": "Resumo comparativo",
  "imagem": "byte-comparativo.svg",
  "colunaA": {
    "titulo": "Keylogger",
    "texto": "Registra o que você digita."
  },
  "colunaB": {
    "titulo": "Screenlogger",
    "texto": "Registra o que aparece na tela."
  },
  "colunaC": {
    "titulo": "Adware",
    "texto": "Coleta hábitos de navegação pra anúncios."
  }
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'VIDEO',
'{
  "titulo": "Spyware e keylogger explicados",
  "imagem": "byte-momento-cinema.svg",
  "urlVideo": "https://youtu.be/vJWn_xvOSNs?si=PuPA79QxIlrLInYE"
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "Um programa registra tudo que você digita no teclado. Que tipo de spyware é esse?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Keylogger",
    "Screenlogger",
    "Adware"
  ],
  "xp": 20,
  "correta": 0,
  "explicacao": "Keylogger é especializado em registrar tudo o que é digitado."
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'QUIZ',
'{
  "pergunta": "Um app coleta seus dados de navegação pra mostrar anúncios personalizados. Isso é:",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Adware",
    "Trojan",
    "Worm"
  ],
  "xp": 20,
  "correta": 0,
  "explicacao": "Adware é o tipo de spyware focado em coleta de dados para anúncios."
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'JOGO',
'{
  "subtipo": "cenario-multipla-escolha",
  "instrucao": "Caça ao Spyware: identifique qual tipo está agindo em cada situação.",
  "xp": 20,
  "rodadas": [
    {
      "situacao": "Enquanto você digita sua senha, um programa registra cada tecla.",
      "opcoes": [
        "Keylogger",
        "Screenlogger",
        "Spyware comum"
      ],
      "respostaCorreta": "Keylogger"
    },
    {
      "situacao": "Um app fotografa sua tela sempre que você abre o banco.",
      "opcoes": [
        "Keylogger",
        "Screenlogger",
        "Adware"
      ],
      "respostaCorreta": "Screenlogger"
    },
    {
      "situacao": "Você pesquisa um tênis e depois só vê anúncios dele.",
      "opcoes": [
        "Keylogger",
        "Screenlogger",
        "Adware"
      ],
      "respostaCorreta": "Adware"
    }
  ],
  "perguntaBonus": {
    "pergunta": "Se você digitasse sua senha em um computador público, isso poderia ser perigoso?",
    "opcoes": [
      "Sim, pode haver um keylogger instalado",
      "Não, computadores públicos são sempre seguros"
    ],
    "respostaCorreta": "Sim, pode haver um keylogger instalado"
  }
}', 4);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (9, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: ",
}', 4);


--==========================================================================
--LICAO 2.5
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Trojan: o cavalo de Troia digital', 5, 60, false, 2)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "O oposto do worm",
  "texto": "Lembra do Worm, que se espalha sozinho? O Trojan é o oposto: ele precisa que você mesmo abra a porta.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "4 min",
  "XP": "+60 XP"
}', 5);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O que é",
  "paragrafos": [
    "O nome vem da história do Cavalo de Troia: os gregos esconderam soldados dentro de um cavalo de madeira pra entrar em Troia sem serem notados. No mundo digital, o malware se esconde dentro de um arquivo que parece seguro.",
    "Diferente do Worm, o Trojan não se espalha sozinho, ele precisa que você baixe e execute o arquivo. Costuma vir disfarçado de anexo de e-mail, download de site desconhecido ou ''presente'' atraente demais pra ser verdade."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "O que um Trojan pode carregar",
  "dica": "Um Trojan pode carregar coisas diferentes dentro de si: alguns instalam uma ''porta secreta'' no sistema (isso é o Backdoor, que você vai conhecer na próxima missão), outros baixam mais malwares, e outros são feitos especificamente pra roubar dados bancários."
}', 5);
 
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 5);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'QUIZ',
'{
  "pergunta": "Um programa parece ser um app normal, mas instala um malware quando você o executa. Que tipo de ameaça é essa?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Spyware",
    "Trojan",
    "Ransomware"
  ],
  "correta": 1,
  "explicacao": "O disfarce de arquivo aparentemente seguro é a marca do Trojan.",
  "xp": 20
}', 5);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "Qual dessas situações pode espalhar um Trojan?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Atualização oficial do sistema",
    "Anexo de e-mail suspeito",
    "Download de aplicativo da loja oficial"
  ],
  "correta": 1,
  "explicacao": "Trojans costumam se disfarçar em anexos de e-mail suspeitos.",
  "xp": 20
}', 5);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'JOGO',
'{
  "subtipo": "classificar-2-categorias",
  "instrucao": "Arraste para o lugar certo: seguro ou perigoso?",
  "categorias": [
    "Seguro",
    "Perigoso"
  ],
  "itens": [
    {
      "texto": "E-mail desconhecido com arquivo compactado .exe",
      "categoria": "Perigoso"
    },
    {
      "texto": "Atualização oficial do sistema",
      "categoria": "Seguro"
    },
    {
      "texto": "''Antivírus gratuito'' baixado de anúncio",
      "categoria": "Perigoso"
    },
    {
      "texto": "Arquivo enviado pelo professor, esperado",
      "categoria": "Seguro"
    }
  ],
  "xp": 20
}', 5);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 5);
 
 
--==========================================================================
--LICAO 2.6
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Ransomware: seus arquivos reféns', 6, 60, false, 2)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "Seus arquivos, reféns",
  "texto": "Imagine abrir seu computador e descobrir que todos os seus arquivos foram trancados, e alguém pede dinheiro pra devolver a chave.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "4-5 min",
  "XP": "+60 XP"
}', 6);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O que é",
  "paragrafos": [
    "Ransomware é um malware que bloqueia o acesso aos seus arquivos. Depois, os criminosos exigem um pagamento (resgate) pra ''liberar'' os dados, mas isso nem sempre acontece de verdade."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "De onde vem o nome",
  "dica": "A palavra vem do inglês ''ransom'' (resgate). Em 2017, o ransomware WannaCry travou computadores em hospitais e empresas de mais de 150 países em poucas horas, chegando a impedir atendimentos médicos."
}', 6);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'COMPARACAO',
'{
  "titulo": "As 4 etapas do ataque",
  "imagem": "byte-comparativo.svg",
  "colunaA": {
    "titulo": "1. Entrada",
    "texto": "Você abre um link, arquivo ou e-mail malicioso."
  },
  "colunaB": {
    "titulo": "2. Instalação",
    "texto": "O ransomware entra sem você perceber."
  },
  "colunaC": {
    "titulo": "3. Bloqueio",
    "texto": "Ele criptografa seus arquivos - ninguém mais abre nada."
  },
  "colunaD": {
    "titulo": "4. Cobrança",
    "texto": "Aparece a cobrança: pague pra (talvez) recuperar tudo."
  }
}', 6);

 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 6);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "Um malware bloqueou seus arquivos e pede dinheiro para liberá-los. Que ataque é esse?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Spyware",
    "Ransomware",
    "Adware"
  ],
  "correta": 1,
  "explicacao": "Ransomware sequestra arquivos e cobra resgate - por isso o nome.",
  "xp": 30
}', 6);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "Qual dessas ações ajuda a se proteger contra ransomware?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Clicar em anúncios desconhecidos",
    "Fazer backup regularmente",
    "Baixar programas de qualquer site"
  ],
  "correta": 1,
  "explicacao": "Backup é a defesa mais eficaz, já que garante que você não perde os arquivos mesmo se eles forem bloqueados.",
  "xp": 30
}', 6);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 6);
 
 
--==========================================================================
--LICAO 2.7
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Backdoor: a porta secreta', 7, 60, false, 2)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "A porta que fica aberta",
  "texto": "Lembra que o Trojan pode ''carregar'' diferentes ataques dentro de si? Um dos mais perigosos é o Backdoor - uma porta que fica aberta pro invasor voltar sempre que quiser.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "2 min",
  "XP": "+60 XP"
}', 7);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O que é",
  "paragrafos": [
    "Backdoor é um mecanismo que cria uma ''porta secreta'' no sistema, permitindo que alguém acesse seu dispositivo remotamente, sem você perceber.",
    "Com essa porta aberta, o invasor pode acessar seu dispositivo à distância, instalar outros malwares ou roubar informações em tempo real - sempre que quiser, não só uma vez."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "Sobrevive à remoção do malware original",
  "dica": "Mesmo que o malware que trouxe o Backdoor seja removido, a porta secreta pode continuar aberta - é por isso que remover só o ''sintoma'' nem sempre resolve o problema de verdade."
}', 7);
 
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 7);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'QUIZ',
'{
  "pergunta": "O que é um Backdoor em segurança da informação?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Um programa que melhora a velocidade do computador",
    "Uma ''porta secreta'' que permite acesso remoto ao sistema",
    "Um tipo de antivírus"
  ],
  "correta": 1,
  "explicacao": "Backdoor cria um acesso remoto persistente e oculto ao sistema.",
  "xp": 20
}', 7);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "O que um invasor pode fazer ao utilizar um Backdoor?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Atualizar automaticamente o sistema",
    "Acessar o dispositivo e instalar outros malwares",
    "Melhorar a segurança do computador"
  ],
  "correta": 1,
  "explicacao": "O Backdoor permite acesso remoto contínuo, usado para instalar mais malwares ou roubar dados.",
  "xp": 20
}', 7);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'JOGO',
'{
  "subtipo": "identificar-em-lista",
  "instrucao": "Detecte o Backdoor: veja a lista de processos e aponte o suspeito.",
  "itens": [
    {
      "texto": "tarefa_escola.pdf",
      "suspeito": false
    },
    {
      "texto": "jogo_gratis.exe",
      "suspeito": false
    },
    {
      "texto": "foto_familia.jpg",
      "suspeito": false
    },
    {
      "texto": "antivirus_premium_crack.exe",
      "suspeito": true,
      "motivo": "Nome genérico + ''crack'' é sinal de alerta comum."
    }
  ],
  "xp": 20
}', 7);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'RESUMO',
'{
  "titulo": "PARABÉNS!!!!! Você terminou o nível 2",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 7);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (9, 'CONCLUSAO_TRILHA',
'{
  "titulo": "Trilha 2 completa: Malware - conheça o inimigo",
  "texto": "Você conheceu os principais malwares: Phishing (engenharia social), Scareware (susto falso), Worm (se espalha sozinho), Spyware (espiona em silêncio), Trojan (se disfarça), Ransomware (sequestra arquivos) e Backdoor (porta secreta).",
  "proximaTrilha": {
    "numero": 3,
    "titulo": "Proteção e Hábitos Seguros"
  },
  "badge": "trilha-2-completa"
}', 7);
 
 
--=========================================================================
-- NIVEL 3
INSERT INTO level (titulo, descricao, ordem)
VALUES ('Nível 3', 'Adote hábitos que protegem seus dados no dia a dia!', 3);
 
--==========================================================================
--LICAO 3.1
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Senhas fortes: a chave que ninguém adivinha', 1, 60, false, 3)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "A chave da sua casa digital",
  "texto": "Sua senha é a chave da sua casa digital. Nessa missão, você aprende a fazer uma chave que ninguém consegue copiar.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "4 min",
  "XP": "+60 XP"
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "Por que importa",
  "paragrafos": [
    "Se alguém descobre sua senha, pode acessar suas redes sociais, e-mails e até documentos importantes - se passando por você.",
    "Evite dados pessoais (data de nascimento, nome), sequências de teclado (''123456'', ''qwerty'') e palavras óbvias (nome de time, personagem favorito). Tudo isso é fácil de adivinhar ou descobrir nas suas redes sociais.",
    "Use frases longas, com várias palavras, em vez de uma palavra só. Frases são mais fáceis de lembrar e muito mais difíceis de adivinhar. Misturar números e símbolos deixa ainda mais forte."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "Frase > palavra",
  "dica": "''gatinho123'' é uma senha fraca. Mas uma frase como ''meugatocometapetesegundafeira'' é muito mais forte - e mais fácil de lembrar do que parece."
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'VIDEO',
'{
  "titulo": "Como criar senha forte",
  "imagem": "byte-momento-cinema.svg",
  "urlVideo": "https://youtu.be/8zcrg23K-M8?si=apkX-AE-UpoHet6z"
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "Qual dessas é a senha mais segura?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "123456",
    "flamengo10",
    "meucachorroadoradormirdetarde"
  ],
  "correta": 2,
  "explicacao": "Frases longas são muito mais difíceis de adivinhar que palavras curtas ou sequências óbvias.",
  "xp": 20
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "Por que usar sua data de nascimento como senha é arriscado?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Porque essa informação costuma estar disponível em redes sociais",
    "Porque datas são difíceis de lembrar",
    "Não é arriscado, desde que ninguém saiba sua senha"
  ],
  "correta": 0,
  "explicacao": "Dados pessoais públicos são um dos primeiros alvos de tentativa de adivinhação de senha.",
  "xp": 20
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'JOGO',
'{
  "subtipo": "classificar-2-categorias",
  "instrucao": "Essa senha é forte ou fraca?",
  "categorias": [
    "Forte",
    "Fraca"
  ],
  "itens": [
    {
      "texto": "qwerty123",
      "categoria": "Fraca"
    },
    {
      "texto": "nomedocachorro",
      "categoria": "Fraca"
    },
    {
      "texto": "otimoamigoquefazbarulhoquandodorme",
      "categoria": "Forte"
    },
    {
      "texto": "Senha@2024!MinhaBici",
      "categoria": "Forte"
    }
  ],
  "xp": 20
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 1);
 
 
--==========================================================================
--LICAO 3.2
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Uma senha pra cada coisa', 2, 60, false, 3)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "Uma senha só não basta",
  "texto": "Você criou uma senha forte na última missão. Ótimo, mas usa ela em tudo? Isso é um problema.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "3-4 min",
  "XP": "+60 XP"
}', 2);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O problema de repetir",
  "paragrafos": [
    "Usar a mesma senha em vários lugares é como ter uma chave só que abre a casa, o carro e o trabalho. Se alguém pega essa chave, tem acesso a tudo de uma vez.",
    "É como um cofre digital: você guarda todas as suas senhas diferentes dentro dele, e só precisa lembrar de uma senha mestra pra acessar todas as outras."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "Vazamentos são mais comuns do que parece",
  "dica": "Vazamentos de dados acontecem até em sites e apps conhecidos. Se sua senha vazar de um lugar e for a mesma do seu e-mail principal, um problema pequeno vira um problema gigante."
}', 2);

 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 2);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'QUIZ',
'{
  "pergunta": "Por que é perigoso usar a mesma senha em vários sites?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Se um site vazar, todas as suas contas ficam vulneráveis",
    "Isso deixa a senha mais fácil de lembrar, sem riscos",
    "Não é perigoso, desde que a senha seja forte"
  ],
  "correta": 0,
  "explicacao": "Reutilizar senha significa que um único vazamento compromete todas as contas que usam a mesma senha.",
  "xp": 20
}', 2);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "O que é um gerenciador de senhas?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Um app que cria contas automaticamente",
    "Um ''cofre'' digital que guarda várias senhas diferentes com segurança",
    "Um antivírus"
  ],
  "correta": 1,
  "explicacao": "O gerenciador armazena senhas de forma segura, exigindo só uma senha mestra.",
  "xp": 20
}', 2);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'JOGO',
'{
  "subtipo": "cenario-escolha",
  "instrucao": "Uma chave pra cada porta",
  "rodadas": [
    {
      "situacao": "Você usa a mesma senha no e-mail, no banco e nas redes sociais.",
      "opcaoA": "Continuar assim",
      "opcaoB": "Trocar por senhas diferentes",
      "respostaCorreta": "opcaoB",
      "explicacao": "Senhas diferentes por conta evitam que um vazamento comprometa tudo de uma vez."
    }
  ],
  "xp": 20
}', 2);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 2);
 
 
--==========================================================================
--LICAO 3.3
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Verificação em duas etapas', 3, 60, false, 3)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "E se a senha não bastasse?",
  "texto": "E se, mesmo que alguém descubra sua senha, ainda assim não conseguisse entrar na sua conta? É isso que a verificação em duas etapas faz.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "4 min",
  "XP": "+60 XP"
}', 3);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O que é",
  "paragrafos": [
    "É usar pelo menos dois fatores diferentes pra confirmar que é você mesmo entrando na conta, não só a senha.",
    "Algo que você sabe (senha, PIN), algo que você possui (celular, token) e algo que você é (impressão digital, reconhecimento facial)."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "Precisa ser de categorias diferentes",
  "dica": "Pra funcionar de verdade, os dois fatores precisam ser de categorias diferentes. Usar duas senhas não é 2FA de verdade - é só uma senha em dobro. O ideal é senha (o que sabe) + código no celular (o que possui)."
}', 3);

INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 3);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'QUIZ',
'{
  "pergunta": "O que é verificação em duas etapas?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Usar duas senhas diferentes",
    "Usar dois fatores de categorias diferentes pra confirmar identidade",
    "Trocar de senha duas vezes por ano"
  ],
  "correta": 1,
  "explicacao": "2FA de verdade combina fatores de categorias distintas, não duas senhas.",
  "xp": 20
}', 3);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "Qual desses é um exemplo de ''algo que você possui''?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Sua senha",
    "Um código enviado pro seu celular",
    "Sua impressão digital"
  ],
  "correta": 1,
  "explicacao": "O celular que recebe o código representa a categoria ''algo que você possui''.",
  "xp": 20
}', 3);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'JOGO',
'{
  "subtipo": "classificar-3-categorias",
  "instrucao": "Monte o 2FA: classifique cada item na categoria certa.",
  "categorias": [
    "O que você sabe",
    "O que você possui",
    "O que você é"
  ],
  "itens": [
    {
      "texto": "Senha",
      "categoria": "O que você sabe"
    },
    {
      "texto": "Token no celular",
      "categoria": "O que você possui"
    },
    {
      "texto": "Impressão digital",
      "categoria": "O que você é"
    }
  ],
  "xp": 20
}', 3);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 3);
 
 
--==========================================================================
--LICAO 3.4
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('O cadeado da internet', 4, 60, false, 3)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "O cadeadinho conta só parte da história",
  "texto": "Você já reparou no cadeadinho ao lado do endereço de um site? Ele conta uma parte importante da história, mas não tudo.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "4 min",
  "XP": "+60 XP"
}', 4);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O que é HTTPS",
  "paragrafos": [
    "HTTPS é a versão segura de comunicação com um site: os dados trocados entre você e o site são criptografados, então ninguém consegue ''ler'' no meio do caminho.",
    "Um cadeado fechado significa que a comunicação é criptografada, mas não significa que o site é confiável. Sites falsos, criados por golpistas, também podem ter cadeado."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "Sites falsos também podem ter HTTPS",
  "dica": "Golpistas criam sites com nomes bem parecidos com os originais (troca de uma letra, por exemplo). Se você não checar o endereço com atenção, o cadeado fechado não te protege de nada."
}', 4);
 
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 4);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'QUIZ',
'{
  "pergunta": "O cadeado fechado no navegador significa que:",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "O site é 100% confiável",
    "A comunicação com o site é criptografada",
    "O site nunca pode ser falso"
  ],
  "correta": 1,
  "explicacao": "O cadeado indica criptografia, não legitimidade do site.",
  "xp": 20
}', 4);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "Antes de digitar sua senha em um site, o que você deve verificar?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Só o cadeado",
    "O cadeado E o endereço do site",
    "Nada, o cadeado já garante tudo"
  ],
  "correta": 1,
  "explicacao": "É necessário conferir tanto o cadeado quanto o endereço exato do site.",
  "xp": 20
}', 4);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'JOGO',
'{
  "subtipo": "classificar-2-categorias",
  "instrucao": "Esse endereço é confiável ou suspeito?",
  "categorias": [
    "Confiável",
    "Suspeito"
  ],
  "itens": [
    {
      "texto": "https://meubanco.com.br",
      "categoria": "Confiável"
    },
    {
      "texto": "https://meu-banc0.com.br",
      "categoria": "Suspeito",
      "observacao": "Zero no lugar do ''o''"
    },
    {
      "texto": "http://loja-oficial.com",
      "categoria": "Suspeito",
      "observacao": "Sem HTTPS"
    }
  ],
  "xp": 20
}', 4);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 4);
 
 
--==========================================================================
--LICAO 3.5
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Cuidado com o wifi público', 5, 60, false, 3)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "O wifi grátis tem um custo escondido",
  "texto": "Aquele wifi grátis do shopping ou da cafeteria é conveniente, mas também pode ser um risco que você nem imagina.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "3 min",
  "XP": "+60 XP"
}', 5);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "Por que é arriscado",
  "paragrafos": [
    "Redes públicas costumam ter segurança mais fraca, e outras pessoas conectadas na mesma rede podem, em certos casos, interceptar o que você envia e recebe."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg"
}', 5);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'COMPARACAO',
'{
  "titulo": "O que evitar",
  "imagem": "byte-comparativo.svg",
  "colunaA": {
    "titulo": "Evite",
    "texto": "Acessar o app do banco enquanto estiver no wifi público."
  },
  "colunaB": {
    "titulo": "Evite",
    "texto": "Trocar senhas importantes conectado a essas redes."
  }
}', 5);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 5);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "Por que redes wifi públicas são mais arriscadas?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Costumam ter segurança mais fraca e podem ser monitoradas por outros",
    "Elas são sempre mais rápidas",
    "Não existe risco nenhum"
  ],
  "correta": 0,
  "explicacao": "A segurança fraca típica dessas redes facilita interceptação de dados.",
  "xp": 20
}', 5);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "O que você deve evitar fazer conectado a um wifi público?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Ler notícias",
    "Acessar o app do banco",
    "Ver o horário"
  ],
  "correta": 1,
  "explicacao": "Atividades sensíveis como acesso bancário devem ser evitadas em redes públicas.",
  "xp": 20
}', 5);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'JOGO',
'{
  "subtipo": "classificar-2-categorias",
  "instrucao": "Pode ou não pode no wifi público?",
  "categorias": [
    "Pode",
    "Evitar"
  ],
  "itens": [
    {
      "texto": "Acessar o banco",
      "categoria": "Evitar"
    },
    {
      "texto": "Ler uma notícia",
      "categoria": "Pode"
    },
    {
      "texto": "Trocar senha",
      "categoria": "Evitar"
    },
    {
      "texto": "Ver um vídeo",
      "categoria": "Pode"
    }
  ],
  "xp": 20
}', 5);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (9, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 5);
 
 
--==========================================================================
--LICAO 3.6
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Cookies: quem está de olho', 6, 60, false, 3)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "Uma forma comum de deixar rastro",
  "texto": "Lembra da sua ''pegada digital'', na Trilha 1? Os cookies são uma das formas mais comuns de deixar rastro. Vamos entender como funcionam.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "4 min",
  "XP": "+60 XP"
}', 6);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O que são",
  "paragrafos": [
    "Cookies são pequenos arquivos de texto guardados no seu dispositivo enquanto você navega. Eles têm funções diferentes dependendo do tipo.",
    "Cookies de sessão: São temporários, existem só enquanto o navegador está aberto. É o que mantém seu carrinho de compras cheio enquanto você navega num site.",
    "Cookies persistentes: Ficam salvos por um tempo definido (semanas ou meses), guardando preferências ou mantendo seu login ativo em sites que você usa com frequência.",
    "Cookies de terceiros: Usados por empresas de anúncios pra rastrear sua navegação em vários sites diferentes, personalizando os anúncios que você vê."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg"
}', 6);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'COMPARACAO',
'{
  "titulo": "Resumo comparativo",
  "imagem": "byte-comparativo.svg",
  "colunaA": {
    "titulo": "Sessão",
    "texto": "Some quando você fecha o navegador."
  },
  "colunaB": {
    "titulo": "Persistente",
    "texto": "Fica salvo por semanas ou meses."
  },
  "colunaC": {
    "titulo": "Terceiros",
    "texto": "Rastreia você em vários sites, pra anúncios."
  }
}', 6);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'VIDEO',
'{
  "titulo": "O que são cookies",
  "imagem": "byte-momento-cinema.svg",
  "urlVideo": "https://youtu.be/MXM9BMU_AJ4?si=O7R7GWmC18xIwkr_"
}', 6);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 6);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "Qual tipo de cookie mantém seu carrinho de compras enquanto você navega?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Cookie de sessão",
    "Cookie persistente",
    "Cookie de terceiros"
  ],
  "correta": 0,
  "explicacao": "Cookies de sessão existem apenas durante a navegação atual.",
  "xp": 20
}', 6);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'QUIZ',
'{
  "pergunta": "Qual tipo de cookie é usado para rastrear você em vários sites diferentes?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Cookie de sessão",
    "Cookie persistente",
    "Cookie de terceiros"
  ],
  "correta": 2,
  "explicacao": "Cookies de terceiros são usados por anunciantes para rastreamento entre sites.",
  "xp": 20
}', 6);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'JOGO',
'{
  "subtipo": "cenario-multipla-escolha",
  "instrucao": "Que cookie é esse?",
  "rodadas": [
    {
      "situacao": "Seu carrinho de compras continua cheio enquanto você navega no mesmo site.",
      "opcoes": [
        "Sessão",
        "Persistente",
        "Terceiros"
      ],
      "respostaCorreta": "Sessão"
    },
    {
      "situacao": "Você fecha e abre o navegador semanas depois e continua logado.",
      "opcoes": [
        "Sessão",
        "Persistente",
        "Terceiros"
      ],
      "respostaCorreta": "Persistente"
    },
    {
      "situacao": "Você pesquisa um produto e passa a ver anúncios dele em sites diferentes.",
      "opcoes": [
        "Sessão",
        "Persistente",
        "Terceiros"
      ],
      "respostaCorreta": "Terceiros"
    }
  ],
  "xp": 20
}', 6);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (9, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 6);
 
 
--==========================================================================
--LICAO 3.7
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Firewall e antivírus: seus guardas', 7, 60, false, 3)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "Duas ferramentas de defesa",
  "texto": "Você já conheceu vários tipos de ataque. Agora conheça duas das principais ferramentas de defesa.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "3-4 min",
  "XP": "+60 XP"
}', 7);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O que é firewall",
  "paragrafos": [
    "Um firewall é uma barreira entre a rede do seu dispositivo e o resto da internet, ele filtra o que entra e sai, bloqueando acessos não autorizados.",
    "O antivírus verifica arquivos e programas em busca de malware conhecido, podendo detectar e remover ameaças já presentes no dispositivo."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "Eles se complementam",
  "dica": "Firewall e antivírus fazem trabalhos diferentes e se complementam: o firewall tenta impedir a entrada, o antivírus lida com o que já entrou."
}', 7);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'VIDEO',
'{
  "titulo": "Firewall x antivírus",
  "imagem": "byte-momento-cinema.svg",
  "urlVideo": "https://youtube.com/shorts/y8Pav1fsq8Q?si=q576d646aval2lF9"
}', 7);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 7);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "Qual é a principal função de um firewall?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Filtrar o tráfego de entrada e saída da rede",
    "Remover vírus já instalados",
    "Criar senhas fortes"
  ],
  "correta": 0,
  "explicacao": "O firewall atua como barreira de rede, filtrando tráfego.",
  "xp": 20
}', 7);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "Qual é a principal função de um antivírus?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Bloquear todo o tráfego de internet",
    "Detectar e remover malware do dispositivo",
    "Criar backups automáticos"
  ],
  "correta": 1,
  "explicacao": "O antivírus busca, detecta e remove malware já presente.",
  "xp": 20
}', 7);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'JOGO',
'{
  "subtipo": "classificar-2-categorias",
  "instrucao": "Firewall ou antivírus?",
  "categorias": [
    "Firewall",
    "Antivírus"
  ],
  "itens": [
    {
      "texto": "Bloqueou uma tentativa de acesso não autorizado à rede",
      "categoria": "Firewall"
    },
    {
      "texto": "Encontrou e removeu um Trojan já instalado",
      "categoria": "Antivírus"
    }
  ],
  "xp": 20
}', 7);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 7);
 
 
--==========================================================================
--LICAO 3.8
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Comprando online com segurança', 8, 60, false, 3)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "O preço bom demais",
  "texto": "Achou o preço mais baixo numa loja que você nunca ouviu falar? Antes de comprar, vale a pena investigar um pouco.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "4 min",
  "XP": "+60 XP"
}', 8);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O risco",
  "paragrafos": [
    "Lojas falsas online costumam atrair vítimas com preços bons demais pra serem verdade, e depois somem com o pagamento sem entregar nada."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg"
}', 8);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'COMPARACAO',
'{
  "titulo": "O que verificar antes de comprar",
  "imagem": "byte-comparativo.svg",
  "colunaA": {
    "titulo": "Reclamações",
    "texto": "Busque o nome da loja + ''reclamações'' ou ''é confiável'' num site de busca."
  },
  "colunaB": {
    "titulo": "Dados de contato",
    "texto": "Verifique se o site mostra CNPJ, endereço físico e contato - lojas sérias não escondem isso."
  },
  "colunaC": {
    "titulo": "CNPJ",
    "texto": "Confira o CNPJ no site da Receita Federal e compare com o nome da empresa."
  }
}', 8);
 
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 8);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "O que é um bom sinal de que uma loja online é confiável?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Ela mostra CNPJ, endereço e contatos claros no site",
    "Ela tem o preço mais baixo do mercado",
    "Ela pede pagamento só por PIX"
  ],
  "correta": 0,
  "explicacao": "Transparência de dados é um forte indício de legitimidade.",
  "xp": 20
}', 8);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "Antes de comprar numa loja desconhecida, o que você pode fazer?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Comprar rápido, antes que o preço suba",
    "Pesquisar o nome da loja + ''reclamações'' num site de busca",
    "Nada, não há como verificar"
  ],
  "correta": 1,
  "explicacao": "Pesquisar reclamações de outros clientes ajuda a identificar golpes.",
  "xp": 20
}', 8);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'JOGO',
'{
  "subtipo": "cenario-escolha",
  "instrucao": "Compraria dessa loja?",
  "rodadas": [
    {
      "situacao": "Loja sem CNPJ visível, com preço muito abaixo do mercado.",
      "opcaoA": "Compraria",
      "opcaoB": "Não compraria",
      "respostaCorreta": "opcaoB",
      "explicacao": "Falta de CNPJ e preço suspeito são sinais de alerta."
    },
    {
      "situacao": "Loja com CNPJ visível, endereço físico e reclamações positivas de clientes.",
      "opcaoA": "Compraria",
      "opcaoB": "Não compraria",
      "respostaCorreta": "opcaoA",
      "explicacao": "Transparência e boas avaliações são bons sinais de confiabilidade."
    }
  ],
  "xp": 20
}', 8);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 8);
 
 
--==========================================================================
--LICAO 3.9
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Backup: sua rede de segurança', 9, 60, false, 3)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "A defesa real contra o ransomware",
  "texto": "Lembra do Ransomware, na Trilha 2? A defesa real contra ele e contra qualquer perda de arquivo - é o backup.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "4 min",
  "XP": "+60 XP"
}', 9);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O que é e por que importa",
  "paragrafos": [
    "Backup é ter uma cópia dos seus arquivos importantes guardada em outro lugar. Computadores quebram, arquivos são apagados por engano, dispositivos são roubados e imprevistos acontecem."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "Use mais de uma forma",
  "dica": "O ideal é usar mais de uma forma de backup ao mesmo tempo assim, se uma falhar, a outra ainda protege seus arquivos."
}', 9);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'COMPARACAO',
'{
  "titulo": "Formas de backup",
  "imagem": "byte-comparativo.svg",
  "colunaA": {
    "titulo": "Nuvem",
    "texto": "Prática e automática, mas pode ter custo se precisar de muito espaço."
  },
  "colunaB": {
    "titulo": "HD externo / pendrive",
    "texto": "Só você tem acesso, mas exige lembrar de atualizar."
  }
}', 9);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 9);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "Por que fazer backup é importante?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Porque imprevistos como perda ou infecção podem apagar seus arquivos",
    "Só é importante para empresas, não para pessoas",
    "Backup é útil só contra vírus, nada mais"
  ],
  "correta": 0,
  "explicacao": "Backup protege contra qualquer tipo de perda de dados, não só ataques.",
  "xp": 20
}', 9);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "Qual é uma boa prática de backup?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Fazer backup só uma vez na vida",
    "Usar mais de uma forma de backup ao mesmo tempo",
    "Guardar tudo só no próprio celular"
  ],
  "correta": 1,
  "explicacao": "Redundância entre métodos de backup reduz o risco de perda total.",
  "xp": 20
}', 9);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'JOGO',
'{
  "subtipo": "classificar-2-categorias",
  "instrucao": "Onde eu guardo isso? Vale a pena fazer backup?",
  "categorias": [
    "Vale a pena backup",
    "Não precisa"
  ],
  "itens": [
    {
      "texto": "Foto de família",
      "categoria": "Vale a pena backup"
    },
    {
      "texto": "Trabalho da escola",
      "categoria": "Vale a pena backup"
    },
    {
      "texto": "Jogo baixado da internet",
      "categoria": "Não precisa"
    }
  ],
  "xp": 20
}', 9);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (9, 'RESUMO',
'{
  "titulo": "PARABÉNS!!!!! Você terminou o nível 3",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 9);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (10, 'CONCLUSAO_TRILHA',
'{
  "titulo": "Trilha 3 completa: Proteção e Hábitos Seguros",
  "texto": "Você aprendeu a criar senhas fortes, usar 2FA, reconhecer HTTPS de verdade, se cuidar em wifi público, entender cookies, diferenciar firewall de antivírus, comprar online com segurança e fazer backup.",
  "proximaTrilha": {
    "numero": 4,
    "titulo": "Já fui infectado, e agora?"
  },
  "badge": "trilha-3-completa"
}', 9);
 
 
--=========================================================================
-- NIVEL 4
INSERT INTO level (titulo, descricao, ordem)
VALUES (' Nível 4', 'Saiba o que fazer se algo der errado!', 4);
 
--==========================================================================
--LICAO 4.1
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Reconhecendo os sinais de infecção', 1, 60, false, 4)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "Sinais que ajudam a identificar cedo",
  "texto": "Mesmo com todo cuidado, às vezes um dispositivo é infectado. A boa notícia: existem sinais que ajudam a identificar isso cedo - e resolver o problema.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "4 min",
  "XP": "+60 XP"
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "Os sinais mais comuns",
  "paragrafos": [
    "Lentidão fora do comum, apps abrindo sozinhos, bateria acabando rápido demais, pop-ups aparecendo o tempo todo, e mensagens enviadas que você não lembra de ter escrito."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "A maioria dos casos tem solução",
  "dica": "Se o seu dispositivo apresenta um ou mais desses sinais, a primeira reação não deve ser pânico. A maioria dos casos de infecção tem solução, seguindo alguns passos, que você vai aprender na próxima missão."
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'COMPARACAO',
'{
  "titulo": "Sinais de alerta",
  "imagem": "byte-comparativo.svg",
  "colunaA": {
    "titulo": "Lentidão",
    "texto": "Lentidão repentina e sem explicação."
  },
  "colunaB": {
    "titulo": "Apps sozinhos",
    "texto": "Apps ou sites abrindo sozinhos."
  },
  "colunaC": {
    "titulo": "Mensagens estranhas",
    "texto": "Mensagens ou e-mails enviados que você não escreveu."
  }
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'VIDEO',
'{
  "titulo": "Sinais de infecção",
  "imagem": "byte-momento-cinema.svg",
  "urlVideo": "https://youtu.be/NROsObJ5ABs?si=wvfmi7rNDV64gTNt"
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'QUIZ',
'{
  "pergunta": "Qual desses é um sinal comum de dispositivo infectado?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Apps abrindo sozinhos, sem você tocar em nada",
    "O celular estar com pouca memória disponível",
    "A tela estar com brilho baixo"
  ],
  "correta": 0,
  "explicacao": "Apps abrindo sozinhos são um sinal clássico de comportamento anômalo por infecção.",
  "xp": 20
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'QUIZ',
'{
  "pergunta": "Qual é a primeira reação recomendada ao notar sinais de infecção?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Entrar em pânico e desligar o celular pra sempre",
    "Manter a calma - a maioria dos casos tem solução",
    "Ignorar, provavelmente vai passar sozinho"
  ],
  "correta": 1,
  "explicacao": "Manter a calma permite seguir os passos corretos de remoção sem prejudicar mais o dispositivo.",
  "xp": 20
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'JOGO',
'{
  "subtipo": "classificar-2-categorias",
  "instrucao": "É sinal de infecção ou é normal?",
  "categorias": [
    "Sinal de alerta",
    "Normal"
  ],
  "itens": [
    {
      "texto": "Bateria acabando muito mais rápido que o normal",
      "categoria": "Sinal de alerta"
    },
    {
      "texto": "Celular esquentando um pouco após jogo pesado",
      "categoria": "Normal"
    },
    {
      "texto": "Pop-ups aparecendo repetidamente, mesmo fora do navegador",
      "categoria": "Sinal de alerta"
    },
    {
      "texto": "App demorando alguns segundos a mais após atualização",
      "categoria": "Normal"
    }
  ],
  "xp": 20
}', 1);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (9, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 1);
 
 
--==========================================================================
--LICAO 4.2
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Removendo a ameaça, passo a passo', 2, 60, false, 4)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "A parte prática",
  "texto": "Identificou os sinais? Agora vem a parte prática: os passos pra remover a ameaça e recuperar seu dispositivo.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "5 min",
  "XP": "+60 XP"
}', 2);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "Etapa 1: Desconecte da internet",
  "paragrafos": [
    "Desligue wifi e dados móveis. A maioria dos malwares só consegue roubar mais dados ou se espalhar enquanto o dispositivo está conectado.",
    "Reinicie o dispositivo no modo seguro. Isso desativa temporariamente apps de terceiros, facilitando encontrar o que está causando o problema.",
    "Veja os apps instalados mais recentemente, geralmente é aí que está a causa. Desinstale qualquer um que pareça suspeito ou que você não reconheça.",
    "Use um antivírus conhecido pra verificar se ainda há resquícios de malware. Desconfie de apps ''milagrosos'' que prometem limpar tudo em segundos.",
    "Limpe os dados de navegação (histórico, cookies, cache) e revise as extensões instaladas no navegador - algumas podem estar comprometidas."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "Cuidado com apps milagrosos",
  "dica": "Cuidado com aplicativos de ''limpeza mágica'' cheios de propaganda, muitos deles não fazem nada de útil, e alguns até pioram o problema."
}', 2);
 
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 2);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'QUIZ',
'{
  "pergunta": "Qual é o primeiro passo recomendado ao suspeitar de uma infecção?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Desconectar da internet (wifi e dados móveis)",
    "Rodar um antivírus imediatamente",
    "Reiniciar o dispositivo normalmente"
  ],
  "correta": 0,
  "explicacao": "Cortar a conexão impede que o malware roube mais dados ou se espalhe.",
  "xp": 20
}', 2);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "Por que o modo de segurança ajuda nesse processo?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Porque deixa o dispositivo mais rápido",
    "Porque desativa apps de terceiros, facilitando identificar o problema",
    "Porque impede qualquer app de abrir para sempre"
  ],
  "correta": 1,
  "explicacao": "O modo de segurança isola apps de terceiros, facilitando a identificação da causa.",
  "xp": 20
}', 2);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (6, 'JOGO',
'{
  "subtipo": "ordenar-sequencia",
  "instrucao": "Monte a ordem certa dos passos de remoção.",
  "itensEmbaralhados": [
    "Rodar antivírus confiável",
    "Desconectar da internet",
    "Limpar cache e navegador",
    "Desinstalar apps suspeitos",
    "Reiniciar em modo de segurança"
  ],
  "ordemCorreta": [
    "Desconectar da internet",
    "Reiniciar em modo de segurança",
    "Desinstalar apps suspeitos",
    "Rodar antivírus confiável",
    "Limpar cache e navegador"
  ],
  "xp": 20
}', 2);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'RESUMO',
'{
  "titulo": "Fim da Lição!",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 2);
 
 
--==========================================================================
--LICAO 4.3
INSERT INTO lesson (titulo, ordem, xp_total, desafio_final, level_id)
VALUES ('Quando tudo mais falha', 3, 40, false, 4)
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (1, 'ABERTURA',
'{
  "titulo": "Um último recurso com custo alto",
  "texto": "Seguiu todos os passos e o problema continua? Existe um último recurso, mas ele tem um custo alto.",
  "imagem": "byte-inicio-licao.svg",
  "tempo": "3-4 min",
  "XP": "+40 XP"
}', 3);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (2, 'CONTEUDO',
'{
  "titulo": "O que é e quando usar",
  "paragrafos": [
    "Restauração de fábrica apaga tudo do dispositivo e deixa ele como novo, removendo o malware junto com todos os seus dados e aplicativos. Use só quando as outras etapas não resolverem.",
    "Antes de restaurar, confirme que tem backup de fotos, contatos, conversas e arquivos importantes  e guarde a senha da conta associada ao dispositivo, porque ela costuma ser exigida depois da restauração."
  ],
  "imagem": "byte-apresentacao-de-conteudo.svg",
  "titulo2": "Por isso o backup importa tanto",
  "dica": "Lembra da Missão 3-9? É exatamente por causa de momentos como esse que backup regular é tão importante, sem ele restaurar de fábrica significa perder tudo de verdade."
}', 3);

 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (3, 'PARABENS',
'{
  "titulo": "Parabéns por ter chegado até aqui!",
  "texto": "agora vamos checar os seus conhecimentos!",
  "imagem": "byte-inicio-perguntas.svg"
}', 3);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (4, 'QUIZ',
'{
  "pergunta": "Quando a restauração de fábrica deve ser usada?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Como primeira opção, sempre que o dispositivo estiver lento",
    "Como último recurso, quando outras etapas não resolveram",
    "Uma vez por mês, por prevenção"
  ],
  "correta": 1,
  "explicacao": "Restauração de fábrica é uma medida drástica, reservada para quando outras soluções falham.",
  "xp": 20
}', 3);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (5, 'QUIZ',
'{
  "pergunta": "O que você deve garantir antes de restaurar o dispositivo?",
  "imagem": "byte-pergunta.svg",
  "opcoes": [
    "Nada, a restauração cuida de tudo sozinha",
    "Que fotos, contatos e arquivos importantes têm backup",
    "Que o dispositivo está carregado a 100%"
  ],
  "correta": 1,
  "explicacao": "Sem backup prévio, a restauração apaga permanentemente todos os dados.",
  "xp": 20
}', 3);
 

 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (7, 'RESUMO',
'{
  "titulo": "PARABÉNS!!!!! Você terminou o nível 4",
  "imagem": "byte-fim-de-secao.svg",
  "texto": "Total de XP ganho: "
}', 3);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (8, 'CONCLUSAO_TRILHA',
'{
  "titulo": "Trilha 4 completa: Já fui infectado, e agora?",
  "texto": "Você aprendeu a reconhecer sinais de infecção, seguir os passos certos pra remover uma ameaça, e saber quando (e como) usar a restauração de fábrica como último recurso.",
  "badge": "trilha-4-completa"
}', 3);
 
INSERT INTO step (ordem, tipo, conteudo_json, lesson_id)
VALUES (9, 'CONCLUSAO_CURSO',
'{
  "titulo": "VOCÊ DETONOU! Curso completo: SecureByte",
  "texto": "Você completou as 4 trilhas: entende por que a informação vale tanto e o que a cibersegurança protege, conhece os principais malwares e como eles agem, sabe os hábitos de proteção do dia a dia, e sabe o que fazer se algo der errado. Isso não é o fim é a base pra usar a internet com mais consciência todos os dias.",
  "badge": "curso-completo"
}', 3);
 
 