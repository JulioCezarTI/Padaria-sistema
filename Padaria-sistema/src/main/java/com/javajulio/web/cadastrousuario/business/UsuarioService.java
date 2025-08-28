package com.javajulio.web.cadastrousuario.business;

import com.javajulio.web.cadastrousuario.infrastructure.entitys.Padaria;
import com.javajulio.web.cadastrousuario.infrastructure.entitys.Usuario;
import com.javajulio.web.cadastrousuario.infrastructure.repository.PadariaRepository;
import com.javajulio.web.cadastrousuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return usuario;
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com e-mail: " + email));
    }

    public Usuario buscarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public void deletarUsuarioPorEmail(String email) {
        Usuario usuario = buscarUsuarioPorEmail(email);
        usuarioRepository.delete(usuario);
    }

    public Usuario atualizarUsuarioPorId(Integer id, Usuario dadosAtualizados) {
        Usuario usuarioExistente = buscarUsuarioPorId(id);


        usuarioExistente.getNome(dadosAtualizados.getDataNascimento());

        usuarioRepository.save(usuarioExistente);
        return usuarioExistente;
    }

    @Service
    public static class PadariaService {

        @Autowired
        private PadariaRepository repository;

        public List<Padaria> listarTodos() {
            return repository.findAll();
        }

        public Optional<Padaria> buscarPorId(Long id) {
            return repository.findById(id);
        }

        public Optional<Padaria> buscarPorNome(String nome) {
            return repository.findByNome(nome);
        }

        public Padaria salvar(Padaria padaria) {
            return repository.save(padaria);
        }

        public Padaria atualizar(Long id, Padaria padaria) {
            padaria.setId(id);
            return repository.save(padaria);
        }

        public void deletar(Long id) {
            repository.deleteById(id);
        }
    }
}
