package com.example.demo.services;

import org.springframework.stereotype.Service;

//Validações Cálculos Decisões e Regras ficam no services
@Service // faz toda a operacao logica
public class ApiFuncionandoServices {
    public String apifunc(String name){  //no executar ele mostra a mensagem mais o nome (isso e oq aparece na tela inicial)
        return "Api funcionando "+ name;
    }
}
