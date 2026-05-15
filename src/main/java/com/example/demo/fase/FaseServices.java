package com.example.demo.fase;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Regras de negócio e persistência de {@link Fase} via {@link FaseRepository}.
 */
@Service
public class FaseServices {

    @Autowired
    private static FaseRepository faseRepository;

    /** Persiste uma nova fase ou atualiza conforme o estado da entidade. */
    public static Fase salvar(Fase fase) {
        return faseRepository.save(fase);
    }

    /** Marca a fase como concluída ({@code concluida = true}). */
    public Fase marcarComoConcluida(Long id) {
        Fase fase = faseRepository.findById(id).orElseThrow(() -> new RuntimeException("Fase não encontrada"));
        fase.setConcluida(true);

        return faseRepository.save(fase);
    }
    public Fase buscar(Long id) {
        return faseRepository.findById(id).orElseThrow();
    }

    public List<Fase> listarTodos() {
        return faseRepository.findAll();
    }

    /** Atualiza apenas campos não nulos enviados no corpo (título, descrição, concluída). */
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
        if (faseRepository.existsById(id)) {
            throw new RuntimeException("Fase não encontrada");
        }
        faseRepository.deleteById(id);
    }
}