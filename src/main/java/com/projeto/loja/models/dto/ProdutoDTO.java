package com.projeto.loja.models.dto;

public class ProdutoDTO {

    private String descricao;
    private Double precoUnitario;   

    public ProdutoDTO() {
    }

    public ProdutoDTO(String descricao, Double precoUnitario) {
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
    }

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

}