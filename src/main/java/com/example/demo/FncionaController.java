package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FncionaController {

    @GetMapping("/")
    public String home() {
        return "API funcionando";
    }

}
