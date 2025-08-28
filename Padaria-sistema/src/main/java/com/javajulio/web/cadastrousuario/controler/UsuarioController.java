package com.javajulio.web.cadastrousuario.controler;

import com.javajulio.web.cadastrousuario.business.UsuarioService;
import com.javajulio.web.cadastrousuario.infrastructure.entitys.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:63342") // libera seu front (porta 8081)
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // ✅ Cadastro de usuário (retorna 201 Created + usuário salvo)
    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
        Usuario salvo = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // Buscar usuário por e-mail
    @GetMapping
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@RequestParam String email) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    // Listar todos
    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    // Deletar por email
    @DeleteMapping
    public ResponseEntity<Void> deletarUsuarioPorEmail(@RequestParam String email) {
        usuarioService.deletarUsuarioPorEmail(email);
        return ResponseEntity.noContent().build(); // 204 = deletado com sucesso
    }

    // Atualizar por ID
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuarioPorId(@PathVariable Integer id,
                                                         @RequestBody Usuario usuario) {
        Usuario atualizado = usuarioService.atualizarUsuarioPorId(id, usuario);
        return ResponseEntity.ok(atualizado);
    }
}
