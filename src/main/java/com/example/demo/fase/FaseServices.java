package com.example.demo.fase;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FaseServices {

    @Autowired
    private static FaseRepository faseRepository;

    public static Fase salvar(Fase fase){
        return faseRepository.save(fase);
    }

    public Fase marcarComoConcluida(Long id) {
        Fase fase = faseRepository.findById(id).orElseThrow(() -> new RuntimeException("Fase não encontrada"));
        fase.setConcluida(true);

        return faseRepository.save(fase);
    }
    public Fase buscar(Long id) {
        return faseRepository.findById(id).orElseThrow();
    }

    public List<Fase> listarTodos() { //lista todas as fases
        return faseRepository.findAll();
    }

    public Fase atualizarParcial(Long id, @NonNull Fase dados) {
        Fase fase = faseRepository.findById(id).orElseThrow();

        if (dados.getTitulo() != null) {
            fase.setTitulo(dados.getTitulo());
        }if (dados.getDescricao() != null) {
            fase.setDescricao(dados.getDescricao());
        }if (dados.getConcluida() != null) {
            fase.setConcluida(dados.getConcluida());
        }return faseRepository.save(fase);
    }

    public void deletar(Long id) {
        if (faseRepository.existsById(id)) { //caso nn ache o id roda a mensagem
            throw new RuntimeException("Fase não encontrada");
        }
        faseRepository.deleteById(id); //cai como else e deleta fase
    }
}