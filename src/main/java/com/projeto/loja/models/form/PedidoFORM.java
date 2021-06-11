package com.projeto.loja.models.form;

import java.util.Date;
import java.util.List;
import com.projeto.loja.models.Pedido;
import com.projeto.loja.models.Produto;
import com.projeto.loja.repositories.PedidoRepository;


public class PedidoFORM {

    private List<Produto> produtos;

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Pedido toForm(PedidoRepository PedidoR) {
        Double total = 0.0;
        for (Produto P : produtos) {
            total =+ P.getPrecoUnitario();
        }
        Date date = new Date();  
        return new Pedido(total,date,produtos);
    }
}