package com.example.demo.medalha;

import com.example.demo.user.Usuario;
import com.example.demo.user.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedalhaServices {

    private final MedalhaRepository medalhaRepository;
    private final UsuarioMedalhaRepository usuarioMedalhaRepository;
    private final UsuarioRepository usuarioRepository;

    // --- CRUD Medalha ---

    public List<Medalha> listarTodas() {
        return medalhaRepository.findAll();
    }

    public Medalha buscarPorId(Long id) {
        return medalhaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medalha não encontrada"));
    }

    public Medalha criar(Medalha medalha) {
        return medalhaRepository.save(medalha);
    }

    public Medalha atualizar(Long id, Medalha dados) {
        Medalha medalha = buscarPorId(id);

        medalha.setNome(dados.getNome());
        medalha.setEvento(dados.getEvento());
        medalha.setAlvo(dados.getAlvo());
        medalha.setObjConcluido(dados.isObjConcluido());
        medalha.setDescricao(dados.getDescricao());

        return medalhaRepository.save(medalha);
    }

    public void deletar(Long id) {
        medalhaRepository.deleteById(id);
    }

    // Upsert: cria o vínculo se não existir, ou atualiza o progresso se já existir
    public UsuarioMedalha atualizarProgresso(Long usuarioId, Long medalhaId, Integer progresso) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        Medalha medalha = buscarPorId(medalhaId);

        UsuarioMedalha vinculo = usuarioMedalhaRepository
                .findByUsuario_IdAndMedalha_Id(usuarioId, medalhaId)
                .orElseGet(() -> {
                    UsuarioMedalha novo = new UsuarioMedalha();
                    novo.setUsuario(usuario);
                    novo.setMedalha(medalha);
                    return novo;
                });

        vinculo.setProgresso(progresso);

        if (medalha.getAlvo() != null && progresso >= medalha.getAlvo()) {
            vinculo.setConcluida(true);
        }

        return usuarioMedalhaRepository.save(vinculo);
    }

    public UsuarioMedalha buscarMedalhaDoUsuario(Long usuarioId, Long medalhaId) {
        return usuarioMedalhaRepository.findByUsuario_IdAndMedalha_Id(usuarioId, medalhaId)
                .orElseThrow(() -> new EntityNotFoundException("Vínculo usuário-medalha não encontrado"));
    }

    public List<UsuarioMedalha> listarMedalhasDoUsuario(Long usuarioId) {
        return usuarioMedalhaRepository.findByUsuario_Id(usuarioId);
    }
}