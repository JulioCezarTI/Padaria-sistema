package com.javajulio.web.cadastrousuario.business;

import com.javajulio.web.cadastrousuario.infrastructure.entitys.Compradeproduto;
import com.javajulio.web.cadastrousuario.infrastructure.entitys.Padaria;
import com.javajulio.web.cadastrousuario.infrastructure.entitys.Usuario;
import com.javajulio.web.cadastrousuario.infrastructure.repository.CompraRepository;
import com.javajulio.web.cadastrousuario.infrastructure.repository.PadariaRepository;
import com.javajulio.web.cadastrousuario.infrastructure.repository.UsuarioRepository;
import dto.CompraRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraService {
    private final CompraRepository compraRepository;
    private final UsuarioRepository usuarioRepository;
    private final PadariaRepository padariaRepository;

    public Compradeproduto criarCompraComDTO(CompraRequestDTO dto) {
        // busca o usuário
        Usuario usuario = usuarioRepository.findById(Long.valueOf(dto.getUsuarioId()))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // busca os produtos pelo id
        List<Padaria> produtos;
        produtos = dto.getProdutos().stream()
                .map(p -> padariaRepository.findById(p.getId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado")))
                .collect(Collectors.toList());

        // cria a compra
        Compradeproduto compradeproduto = new Compradeproduto();
        compradeproduto.setUsuario(usuario);
        compradeproduto.setProdutos(produtos);
        compradeproduto.setDataCompra(LocalDateTime.now());

        return compraRepository.save(compradeproduto);
    }

    // só para referência: calcula valor total
    public Double calcularValorTotal(List<Padaria> produtos) {
        return produtos.stream()
                .mapToDouble(Padaria::getPreco)
                .sum();
    }
}
