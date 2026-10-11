package com.example.demo.user;

import com.example.demo.excessoes.ResourceNotFoundException;
import com.example.demo.medalha.UsuarioMedalhaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class UsuarioServices {

    private static final Logger log = LoggerFactory.getLogger(UsuarioServices.class);

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMedalhaRepository usuarioMedalhaRepository;
    private final UserLessonProgressRepository progressoRepository;

    public UsuarioServices(UsuarioRepository usuarioRepository,
                           PasswordEncoder passwordEncoder,
                           UsuarioMedalhaRepository usuarioMedalhaRepository,
                           UserLessonProgressRepository progressoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMedalhaRepository = usuarioMedalhaRepository;
        this.progressoRepository = progressoRepository;
    }

    public Usuario salvar(Usuario usuario) {
        String email = normalizarEmail(usuario.getEmail());
        usuario.setEmail(email);
        if (usuarioRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
        if (usuarioRepository.existsByNomeusuario(usuario.getNomeusuario())) {
            throw new IllegalArgumentException("Nome de usuário já cadastrado");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(normalizarEmail(email))
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    /** Busca o usuário e garante que ele é o próprio usuário logado. */
    public Usuario buscarDoUsuario(Long id, String emailAutenticado) {
        Usuario usuario = buscar(id);
        if (!usuario.getEmail().equalsIgnoreCase(emailAutenticado)) {
            throw new AccessDeniedException("Acesso não autorizado");
        }
        return usuario;
    }

    public Usuario atualizarParcial(Long id, Usuario dados, String emailAutenticado) {
        Usuario usuario = buscarDoUsuario(id, emailAutenticado);

        if (dados.getNome() != null && !dados.getNome().isBlank()) {
            usuario.setNome(dados.getNome().trim());
        }
        if (dados.getEmail() != null && !dados.getEmail().isBlank()) {
            String novoEmail = normalizarEmail(dados.getEmail());
            if (!novoEmail.equalsIgnoreCase(usuario.getEmail()) && usuarioRepository.existsByEmail(novoEmail)) {
                throw new IllegalArgumentException("E-mail já cadastrado");
            }
            usuario.setEmail(novoEmail);
        }
        // Senha não é alterada por aqui (veja atualizarSeguranca).
        return usuarioRepository.save(usuario);
    }

    /**
     * Tela de segurança: troca e-mail e/ou senha. Sempre exige a senha atual.
     * O progresso não se perde porque ele é ligado ao id do usuário, não ao e-mail.
     */
    public Usuario atualizarSeguranca(Long id, String senhaAtual, String novaSenha,
                                      String novoEmail, String emailAutenticado) {
        Usuario usuario = buscarDoUsuario(id, emailAutenticado);

        if (senhaAtual == null || !passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new IllegalArgumentException("Senha incorreta");
        }

        boolean querTrocarEmail = novoEmail != null && !novoEmail.isBlank();
        boolean querTrocarSenha = novaSenha != null && !novaSenha.isBlank();
        if (!querTrocarEmail && !querTrocarSenha) {
            throw new IllegalArgumentException("Informe um novo e-mail ou uma nova senha");
        }

        if (querTrocarEmail) {
            String email = normalizarEmail(novoEmail);
            if (email.length() < 7 || email.length() > 150 || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                throw new IllegalArgumentException("E-mail inválido");
            }
            if (!email.equals(usuario.getEmail())) {
                if (usuarioRepository.existsByEmail(email)) {
                    throw new IllegalArgumentException("E-mail já cadastrado");
                }
                usuario.setEmail(email);
            }
        }

        if (querTrocarSenha) {
            if (novaSenha.length() < 8) {
                throw new IllegalArgumentException("A nova senha deve possuir pelo menos 8 caracteres");
            }
            if (novaSenha.length() > 72) {
                throw new IllegalArgumentException("A nova senha deve possuir no máximo 72 caracteres");
            }
            if (passwordEncoder.matches(novaSenha, usuario.getSenha())) {
                throw new IllegalArgumentException("A nova senha deve ser diferente da atual");
            }
            usuario.setSenha(passwordEncoder.encode(novaSenha));
        }

        return usuarioRepository.save(usuario);
    }

    /**
     * Exclui a conta de vez: exige a senha, apaga medalhas e progresso do usuário
     * (senão a chave estrangeira impede o delete) e por fim o próprio usuário.
     */
    @Transactional
    public void deletar(Long id, String senha, String motivo, String comentario, String emailAutenticado) {
        Usuario usuario = buscarDoUsuario(id, emailAutenticado);

        if (senha == null || !passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new IllegalArgumentException("Senha incorreta");
        }

        usuarioMedalhaRepository.deleteByUsuario_Id(id);
        progressoRepository.deleteByUsuario_Id(id);
        usuarioRepository.deleteById(id);

        // Só o motivo fica registrado no log; nenhum dado do usuário é mantido.
        log.info("Conta {} excluída. Motivo: {} | Comentário: {}", id, motivo, comentario);
    }

    private String normalizarEmail(String email) {
        return email == null ? null : email.trim().toLowerCase(Locale.ROOT);
    }
}