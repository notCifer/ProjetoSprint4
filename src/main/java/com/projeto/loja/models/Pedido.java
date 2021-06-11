package com.projeto.loja.models;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private List<Produto> produto;
    @ManyToOne
    private Pessoa pessoa;

    public Pedido() {
    }

    public Pedido(Pessoa pessoa, List<Produto> produtos, Double total){
        this.pessoa = pessoa;
        this.produto = produtos;
        this.date = LocalDateTime.now();         
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Produto> getIdproduto() {
        return produto;
    }

    public void setIdproduto(List<Produto> idproduto) {
        this.produto = idproduto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }

}
