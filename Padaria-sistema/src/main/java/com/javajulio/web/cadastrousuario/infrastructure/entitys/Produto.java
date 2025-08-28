package com.javajulio.web.cadastrousuario.infrastructure.entitys;



import jakarta.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Double preco;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compradeproduto compra;

    // ===== Getters e Setters =====

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Compradeproduto getCompra() {
        return compra;
    }

    public void setCompra(Compradeproduto compra) {
        this.compra = compra;
    }
}

