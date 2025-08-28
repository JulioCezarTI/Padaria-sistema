package com.javajulio.web.cadastrousuario.infrastructure.entitys;

import jakarta.persistence.*;

@Entity
public class CompraProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;
    private int quantidade;
    private double preco;

    @ManyToOne
    private Compradeproduto compra;
}
