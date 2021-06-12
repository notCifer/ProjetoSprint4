package com.projeto.loja.models.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.projeto.loja.models.Produto;
import com.projeto.loja.repositories.ProdutoRepository;

public class ProdutoFORM {

    @NotBlank
    private String descricao;
    @NotNull
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
        Produto produto = new Produto(descricao, precoUnitario);
        ProdutoR.save(produto);
        return produto;
    }

}