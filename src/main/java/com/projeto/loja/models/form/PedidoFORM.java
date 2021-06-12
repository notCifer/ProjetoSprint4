package com.projeto.loja.models.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.projeto.loja.models.Pedido;
import com.projeto.loja.models.Pessoa;
import com.projeto.loja.models.Produto;
import com.projeto.loja.repositories.PedidoRepository;
import com.projeto.loja.repositories.PessoaRepository;
import com.projeto.loja.repositories.ProdutoRepository;

public class PedidoFORM {

    @NotNull
    private Long idpessoa;
    @NotNull @NotEmpty
    private List<Long> idproduto;

    public void setIdproduto(List<Long> idproduto) {
        this.idproduto = idproduto;
    }

    public void setIdpessoa(Long idpessoa) {
        this.idpessoa = idpessoa;
    }

    public Pedido toForm(PedidoRepository PedidoR, PessoaRepository PessoaR, ProdutoRepository ProdutoR) {
        Double total = 0.0;
        Pessoa pessoa = PessoaR.getById(idpessoa);
        List<Produto> produtos = new ArrayList<>();

        for (Long idp : idproduto) {

            Produto produto = ProdutoR.getById(idp);
            total += produto.getPrecoUnitario();
            produtos.add(produto);

        }

        Pedido pedido = new Pedido(pessoa, produtos, total);
        PedidoR.save(pedido);
        return pedido;
    }

}