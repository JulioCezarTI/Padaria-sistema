package com.javajulio.web.cadastrousuario.controler;


import com.javajulio.web.cadastrousuario.business.CompraService;
import com.javajulio.web.cadastrousuario.infrastructure.entitys.Compradeproduto;
import dto.CompraRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {
    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<Compradeproduto> realizarcompra(@RequestBody CompraRequestDTO dto) {
        Compradeproduto novaCompra = compraService.criarCompraComDTO(dto);
        return ResponseEntity.ok(novaCompra);
    }
}
