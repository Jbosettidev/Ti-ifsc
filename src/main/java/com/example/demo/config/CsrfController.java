package com.example.demo.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * CSRF está desligado, mas as telas (login, segurança) ainda fazem fetch('/csrf') e esperam
 * um JSON com "token". Devolvemos um token vazio só para elas continuarem funcionando.
 */
@RestController
public class CsrfController {

    @GetMapping("/csrf")
    public Map<String, String> csrf() {
        return Map.of("token", "");
    }
}
