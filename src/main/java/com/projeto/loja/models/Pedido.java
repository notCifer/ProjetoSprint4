package com.projeto.loja.models;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double total;
    private LocalDateTime date;
    @ManyToMany
    @JoinTable(name = "pedido_produtos", joinColumns = @JoinColumn(name = "produto_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pedido_if", referencedColumnName = "id"))
    private List<Produto> produto;
    @ManyToOne
    private Pessoa pessoa;

    public Pedido() {
    }

    public Pedido(Pessoa pessoa, List<Produto> produtos, Double total) {
        this.pessoa = pessoa;
        this.produto = produtos;
        this.date = LocalDateTime.now();
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

}
