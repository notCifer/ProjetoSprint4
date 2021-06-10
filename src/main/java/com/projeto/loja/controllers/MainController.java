package com.projeto.loja.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.projeto.loja.models.Endereco;
import com.projeto.loja.models.Pedido;
import com.projeto.loja.models.Pessoa;
import com.projeto.loja.models.Produto;
import com.projeto.loja.repositories.EnderecoRepository;
import com.projeto.loja.repositories.PedidoRepository;
import com.projeto.loja.repositories.PessoaRepository;
import com.projeto.loja.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private PessoaRepository PessoaR;

    @Autowired
    private ProdutoRepository ProdutoR;

    @Autowired
    private EnderecoRepository EnderecoR;

    @Autowired
    private PedidoRepository PedidoR;

    // @Autowired
    // private ModelMapper mapper;

    // ______________METODOS PESSOAS______________

    // @RequestMapping(value = "/loja/pessoa", method = RequestMethod.GET)
    // public List<PessoaDTO> FindAllPessoas() {
    //     return PessoaR.findAll().stream().map(this::toPessoaDTO).collect(Collectors.toList());
    // }

    @RequestMapping(value = "/loja/pessoa", method = RequestMethod.POST)
    public Pessoa AddPessoa(@RequestBody Pessoa P) {
        return PessoaR.save(P);
    }

    // private PessoaDTO toPessoaDTO(Pessoa pessoa){
    //     return mapper.map(pessoa, PessoaDTO.class);
    // }

    // ______________METODOS PRODUTOS______________
    @RequestMapping(value = "/loja/produto", method = RequestMethod.GET)
    public List<Produto> findAllProdutos() {
        return ProdutoR.findAll();
    }

    @RequestMapping(value = "/loja/produto", method = RequestMethod.POST)
    public Produto addProduto(@RequestBody Produto P) {
        return ProdutoR.save(P);
    }

    // ______________METODOS ENDERECOS______________
    @RequestMapping(value = "/loja/endereco", method = RequestMethod.GET)
    public List<Endereco> FindAllEnderecos() {
        return EnderecoR.findAll();
    }

    @RequestMapping(value = "/loja/endereco", method = RequestMethod.POST)
    public Endereco AddEndereco(@RequestBody Endereco E) {
        return EnderecoR.save(E);
    }

    // ______________METODOS PEDIDOS______________
    @RequestMapping(value = "/loja/pedido", method = RequestMethod.GET)
    public List<Pedido> FindAllPedidos() {
        return PedidoR.findAll();
    }

    @RequestMapping(value = "/loja/pedido", method = RequestMethod.POST)
    public Pedido AddPedido(@RequestBody Pedido P) {
        return PedidoR.save(P);
    }

}