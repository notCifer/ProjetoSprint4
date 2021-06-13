package com.projeto.loja.models.form;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.projeto.loja.models.Pedido;
import com.projeto.loja.models.Produto;
import com.projeto.loja.repositories.PedidoRepository;
import com.projeto.loja.repositories.ProdutoRepository;

public class PedidoFORM {

    @NotNull
    @NotEmpty
    private List<Long> idproduto;


    public List<Long> getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(List<Long> idproduto) {
        this.idproduto = idproduto;
    }

    public Pedido toForm(PedidoRepository PedidoR, ProdutoRepository ProdutoR) {
        Double total = 0.0;
        List<Produto> produtos = new ArrayList<>();

        for (Long idp : idproduto) {

            Produto produto = ProdutoR.getById(idp);
            total += produto.getPrecoUnitario();
            produtos.add(produto);

        }

        Pedido pedido = new Pedido(produtos, total);
        PedidoR.save(pedido);
        return pedido;
    }

}