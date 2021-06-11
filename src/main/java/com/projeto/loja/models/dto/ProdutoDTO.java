package com.projeto.loja.models.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.projeto.loja.models.Produto;

public class ProdutoDTO {

    private String descricao;
    private Double precoUnitario;

    

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto) {
        this.descricao = produto.getDescricao();
        this.precoUnitario = produto.getPrecoUnitario();
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

    public ProdutoDTO EntidDTO(Produto produto) {
        ProdutoDTO DTO = new ProdutoDTO();
        DTO.setDescricao(produto.getDescricao());
        DTO.setPrecoUnitario(produto.getPrecoUnitario());
        return DTO;
    }

    public List<ProdutoDTO> EntidDTO(List<Produto> produtos) {
        return produtos.stream().map(produto -> EntidDTO(produto)).collect(Collectors.toList());
    }

}