package com.example.demo.user;
//services manda salvar no repositorio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServices {
    //TODO faz o validador aqui, e um exception pra null
    @Autowired //chama a classe que da as consultas sql
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario buscar(Long id) { //lista o id d um usuario (usa pro certificado/validacao)
        return usuarioRepository.findById(id).orElseThrow();
    }

    public List<Usuario> listarTodos() { //lista todos os usuarios
        return usuarioRepository.findAll();
    }

    public Usuario atualizarParcial(Long id, Usuario dados) { //patch, ele atualiza o dado que vir
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        if (dados.getNome() != null) {
            usuario.setNome(dados.getNome());
        }if (dados.getEmail() != null) {
            usuario.setEmail(dados.getEmail());
        }return usuarioRepository.save(usuario); //TODO fazer um para senha,, mas antes fazer um validador pra senha
    }

    public void deletar(Long id) {
        if (usuarioRepository.existsById(id)) { //caso nn ache o id roda a mensagem
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id); //cai como else e deleta usuario
    }
}