package com.example.demo.medalha;

import com.example.demo.user.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medalhas")
@CrossOrigin(origins = "*") //libera a pagina web ter acesso a recursos restritos do webMvc
public class MedalhaController {

    @Autowired
    private MedalhaServices medalhaServices;

    @GetMapping("/{id}")
    public Medalha buscarPorId(@PathVariable Long id) {
        return medalhaServices.buscar(id);
    }

    @GetMapping
    public List<Medalha> listarMedalhas() {
        return medalhaServices.listarTodas();
    }
}
