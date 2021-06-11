package com.projeto.loja.models.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.projeto.loja.models.Pedido;
import com.projeto.loja.models.Produto;

public class PedidoDTO {

    private double total;
    private Date date;
    private List<Produto> produtos;

    public PedidoDTO() {
    }

    public PedidoDTO(Pedido pedido) {
        this.total = pedido.getTotal();
        this.date = pedido.getDate();
        this.produtos = pedido.getProdutos();
    }

    public PedidoDTO(double total, Date date, List<Produto> produtos) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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