package com.projeto.loja.models.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.projeto.loja.models.Pedido;

public class PedidoDTO {

    private double total;
    private LocalDateTime date;
    private List<ProdutoDTO> produtosDTO;

    public void setTotal(double total) {
        this.total = total;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setProdutosDTO(List<ProdutoDTO> produtosDTO) {
        this.produtosDTO = produtosDTO;
    }

    public PedidoDTO EntidDTO(Pedido pedido) {
        PedidoDTO DTO = new PedidoDTO();
        ProdutoDTO DTO2 = new ProdutoDTO();
        DTO.setTotal(pedido.getTotal());
        DTO.setDate(pedido.getDate());
        DTO.setProdutosDTO(DTO2.EntidDTO(pedido.getProduto()));
        return DTO;
    }

    public List<PedidoDTO> EntidDTO(List<Pedido> pedidos) {
        return pedidos.stream().map(pedido -> EntidDTO(pedido)).collect(Collectors.toList());
    }

}