package com.projeto.loja.models.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.projeto.loja.models.Pedido;
import com.projeto.loja.models.Produto;
import com.projeto.loja.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class PedidoDTO {

    @Autowired
    private ProdutoRepository ProdutoR;

    private double total;
    private LocalDateTime date;
    private List<Produto> produtos;

    public PedidoDTO() {
    }

    public PedidoDTO(Pedido pedido) {
        this.total = pedido.getTotal();
        this.date = pedido.getDate();
        // Converter _________________________
        for(int x = 0; x > pedido.getIdproduto().size(); x++){
            Long idprodutoLong=Long.valueOf(x);
            Optional<Produto> produto = ProdutoR.findById(idprodutoLong);
            Produto P = produto.get();
            produtos.add(P);
        }
        // ____________________________________
    }

    public PedidoDTO(double total, LocalDateTime date, List<Produto> produtos) {
        this.total = total;
        this.date = date;
        this.produtos = produtos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public PedidoDTO EntidDTO(Pedido pedido) {
        PedidoDTO DTO = new PedidoDTO();
        DTO.setTotal(total);
        DTO.setDate(date);
        DTO.setProdutos(produtos);
        return DTO;
    }

    public List<PedidoDTO> EntidDTO(List<Pedido> pedidos) {
        return pedidos.stream().map(pedido -> EntidDTO(pedido)).collect(Collectors.toList());
    }

}