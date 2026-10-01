package com.example.demo.medalha;

import com.example.demo.excessoes.ResourceNotFoundException;
import com.example.demo.user.UserLessonProgress;
import com.example.demo.user.UserLessonProgressRepository;
import com.example.demo.user.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedalhaServices {

    private final MedalhaRepository medalhaRepository;
    private final UsuarioMedalhaRepository usuarioMedalhaRepository;
    private final UsuarioRepository usuarioRepository;
    private final UserLessonProgressRepository progressoRepository;

    // ---------------------------------------------------------------- catálogo

    /** Cria as medalhas padrão que ainda não existem. Sem isso, conceder medalha falhava (tabela vazia). */
    @PostConstruct
    void criarMedalhasPadrao() {
        criarSeNaoExiste(MedalhaCodigo.PRIMEIRA_MISSAO, "Primeira missão",
                "Completou sua primeira atividade na plataforma com sucesso.", "LICAO_CONCLUIDA", 1);
        criarSeNaoExiste(MedalhaCodigo.TERMINAR_NIVEL_80, "Quase lá",
                "Terminou um nível com pelo menos 80% de aproveitamento.", "NIVEL_CONCLUIDO", 80);
        criarSeNaoExiste(MedalhaCodigo.TERMINAR_NIVEL_100, "Nota máxima",
                "Terminou um nível com 100% de aproveitamento.", "NIVEL_CONCLUIDO", 100);
        criarSeNaoExiste(MedalhaCodigo.JOGO_FINAL_PONTUACAO_MAXIMA, "Mestre do desafio",
                "Gabaritou o desafio final de um nível.", "DESAFIO_FINAL", 100);
        criarSeNaoExiste(MedalhaCodigo.FINALIZAR_CURSO, "Guardião digital",
                "Concluiu todos os níveis do curso.", "CURSO_CONCLUIDO", 100);
        criarSeNaoExiste(MedalhaCodigo.OFENSIVA_7_DIAS, "Sequência de foco",
                "Manteve acesso diário por 7 dias consecutivos.", "OFENSIVA", 7);
        criarSeNaoExiste(MedalhaCodigo.OFENSIVA_15_DIAS, "Foco total",
                "Manteve acesso diário por 15 dias consecutivos.", "OFENSIVA", 15);
        criarSeNaoExiste(MedalhaCodigo.OFENSIVA_30_DIAS, "Imparável",
                "Manteve acesso diário por 30 dias consecutivos.", "OFENSIVA", 30);
    }

    private void criarSeNaoExiste(String codigo, String nome, String descricao, String evento, int alvo) {
        if (medalhaRepository.findByCodigo(codigo).isPresent()) return;
        Medalha m = new Medalha();
        m.setCodigo(codigo);
        m.setNome(nome);
        m.setDescricao(descricao);
        m.setEvento(evento);
        m.setAlvo(alvo);
        medalhaRepository.save(m);
    }

    // ---------------------------------------------------------------- CRUD

    public List<Medalha> listarTodas() {
        return medalhaRepository.findAll();
    }

    public Medalha buscarPorId(Long id) {
        return medalhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medalha não encontrada"));
    }

    public Medalha criar(Medalha medalha) {
        return medalhaRepository.save(medalha);
    }

    public Medalha atualizar(Long id, Medalha dados) {
        Medalha medalha = buscarPorId(id);
        medalha.setCodigo(dados.getCodigo());
        medalha.setNome(dados.getNome());
        medalha.setEvento(dados.getEvento());
        medalha.setAlvo(dados.getAlvo());
        medalha.setDescricao(dados.getDescricao());
        medalha.setNomeArquivoIcone(dados.getNomeArquivoIcone());
        return medalhaRepository.save(medalha);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        medalhaRepository.deleteById(id);
    }

    // ---------------------------------------------------------------- medalhas do usuário

    public List<UsuarioMedalha> listarMedalhasDoUsuario(Long usuarioId) {
        return usuarioMedalhaRepository.findByUsuario_Id(usuarioId);
    }

    public UsuarioMedalha buscarMedalhaDoUsuario(Long usuarioId, Long medalhaId) {
        return usuarioMedalhaRepository.findByUsuario_IdAndMedalha_Id(usuarioId, medalhaId)
                .orElseThrow(() -> new ResourceNotFoundException("Vínculo usuário-medalha não encontrado"));
    }

    /** Atualiza o progresso manualmente; conclui a medalha quando atinge o alvo. */
    public UsuarioMedalha atualizarProgresso(Long usuarioId, Long medalhaId, Integer progresso) {
        Medalha medalha = buscarPorId(medalhaId);
        UsuarioMedalha vinculo = obterOuCriarVinculo(usuarioId, medalha);
        vinculo.setProgresso(progresso);
        if (medalha.getAlvo() != null && progresso >= medalha.getAlvo()) {
            vinculo.setConcluida(true);
        }
        return usuarioMedalhaRepository.save(vinculo);
    }

    // ---------------------------------------------------------------- regras de concessão

    public void avaliarPrimeiraMissao(Long usuarioId) {
        conceder(usuarioId, MedalhaCodigo.PRIMEIRA_MISSAO);
    }

    public void avaliarMedalhasDeNivel(Long usuarioId, List<UserLessonProgress> progressosDoNivel) {
        double media = progressosDoNivel.stream()
                .filter(Objects::nonNull)
                .mapToDouble(UserLessonProgress::calcularAproveitamento)
                .average()
                .orElse(0.0);

        if (media >= 100.0) {
            conceder(usuarioId, MedalhaCodigo.TERMINAR_NIVEL_100);
        } else if (media >= 80.0) {
            conceder(usuarioId, MedalhaCodigo.TERMINAR_NIVEL_80);
        }
    }

    public void avaliarMedalhaDeCurso(Long usuarioId) {
        conceder(usuarioId, MedalhaCodigo.FINALIZAR_CURSO);
    }

    public void avaliarMedalhaDeJogoFinal(Long usuarioId, int acertos, int totalPerguntas) {
        if (totalPerguntas > 0 && acertos == totalPerguntas) {
            conceder(usuarioId, MedalhaCodigo.JOGO_FINAL_PONTUACAO_MAXIMA);
        }
    }

    /** Conta quantos dias seguidos (até hoje ou ontem) o usuário concluiu alguma lição. */
    public void avaliarOfensiva(Long usuarioId) {
        Set<LocalDate> dias = progressoRepository.findByUsuario_Id(usuarioId).stream()
                .map(UserLessonProgress::getConcluidoEm)
                .filter(Objects::nonNull)
                .map(d -> d.toLocalDate())
                .collect(Collectors.toSet());

        LocalDate dia = dias.contains(LocalDate.now()) ? LocalDate.now() : LocalDate.now().minusDays(1);
        int seguidos = 0;
        while (dias.contains(dia)) {
            seguidos++;
            dia = dia.minusDays(1);
        }

        if (seguidos >= 7) conceder(usuarioId, MedalhaCodigo.OFENSIVA_7_DIAS);
        if (seguidos >= 15) conceder(usuarioId, MedalhaCodigo.OFENSIVA_15_DIAS);
        if (seguidos >= 30) conceder(usuarioId, MedalhaCodigo.OFENSIVA_30_DIAS);
    }

    // ---------------------------------------------------------------- internos

    private void conceder(Long usuarioId, String codigo) {
        Medalha medalha = medalhaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Medalha não encontrada: " + codigo));

        UsuarioMedalha vinculo = obterOuCriarVinculo(usuarioId, medalha);
        if (vinculo.isConcluida()) return;

        vinculo.setProgresso(medalha.getAlvo() != null ? medalha.getAlvo() : 100);
        vinculo.setConcluida(true);
        usuarioMedalhaRepository.save(vinculo);
    }

    private UsuarioMedalha obterOuCriarVinculo(Long usuarioId, Medalha medalha) {
        return usuarioMedalhaRepository.findByUsuario_IdAndMedalha_Id(usuarioId, medalha.getId())
                .orElseGet(() -> {
                    UsuarioMedalha novo = new UsuarioMedalha();
                    novo.setUsuario(usuarioRepository.findById(usuarioId)
                            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado")));
                    novo.setMedalha(medalha);
                    novo.setProgresso(0);
                    return novo;
                });
    }
}
