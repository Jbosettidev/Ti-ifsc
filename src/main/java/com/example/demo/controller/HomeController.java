package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.services.ApiFuncionandoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //so cordena a informacao nn processa nada ,, so chama o services
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private ApiFuncionandoServices apiFuncionandoServices; //add o apiServices

    @GetMapping
    public String home() {
        return apiFuncionandoServices.apifunc("joao");
    }

    @PostMapping //usado quando você quer enviar dados para o servidor,
    public String HelloWorldPost(@RequestBody User body){
        return "Hello World "+ body.getName();
    }
}
