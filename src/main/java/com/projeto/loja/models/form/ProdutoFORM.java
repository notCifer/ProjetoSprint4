package com.projeto.loja.models.form;

import com.projeto.loja.models.Produto;
import com.projeto.loja.repositories.ProdutoRepository;

public class ProdutoFORM {

    private String descricao;
    private Double precoUnitario;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Produto toForm(ProdutoRepository ProdutoR){
        return new Produto(descricao, precoUnitario);
    }

}