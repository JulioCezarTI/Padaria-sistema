package com.javajulio.web.cadastrousuario.controler;


import com.javajulio.web.cadastrousuario.business.UsuarioService;
import com.javajulio.web.cadastrousuario.infrastructure.entitys.Padaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/padaria")
public class PadariaController {

    @Autowired
    private UsuarioService.PadariaService service;

    @GetMapping
    public List<Padaria> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Padaria> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public Optional<Padaria> buscarPorNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
    }

    @PostMapping
    public ResponseEntity<Padaria> salvar(@RequestBody Padaria produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Padaria> atualizar(@PathVariable Long id, @RequestBody Padaria produtoAtualizado) {
        return ResponseEntity.ok(service.atualizar(id, produtoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
