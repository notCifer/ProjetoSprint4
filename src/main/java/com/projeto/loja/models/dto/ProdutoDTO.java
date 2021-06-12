package com.projeto.loja.models.dto;

import java.util.List;
import java.util.stream.Collectors;
import com.projeto.loja.models.Produto;

public class ProdutoDTO {

    private Long id;
    private String descricao;
    private Double precoUnitario;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        DTO.setId(produto.getId());
        DTO.setDescricao(produto.getDescricao());
        DTO.setPrecoUnitario(produto.getPrecoUnitario());
        return DTO;
    }

    public List<ProdutoDTO> EntidDTO(List<Produto> produtos) {
        return produtos.stream().map(produto -> EntidDTO(produto)).collect(Collectors.toList());
    }



}