package com.projeto.loja.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.projeto.loja.models.Pedido;
import com.projeto.loja.models.Pessoa;
import com.projeto.loja.models.Produto;
import com.projeto.loja.models.dto.PedidoDTO;
import com.projeto.loja.models.dto.PessoaDTO;
import com.projeto.loja.models.dto.ProdutoDTO;
import com.projeto.loja.models.form.PedidoFORM;
import com.projeto.loja.models.form.PessoaFORM;
import com.projeto.loja.models.form.ProdutoFORM;
import com.projeto.loja.repositories.EnderecoRepository;
import com.projeto.loja.repositories.PedidoRepository;
import com.projeto.loja.repositories.PessoaRepository;
import com.projeto.loja.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class MainController {

    @Autowired
    private PessoaRepository PessoaR;

    @Autowired
    private ProdutoRepository ProdutoR;

    @Autowired
    private PedidoRepository PedidoR;

    // ______________METODOS PESSOAS______________

    @RequestMapping(value = "/loja/pessoa", method = RequestMethod.GET)
    public ResponseEntity<List<PessoaDTO>> FindAllPessoas() {
        List<Pessoa> findList = PessoaR.findAll();
        PessoaDTO DTO = new PessoaDTO();
        return ResponseEntity.ok().body(DTO.EntidDTO(findList));
    }

    @RequestMapping(value = "/loja/pessoa", method = RequestMethod.POST)
    public ResponseEntity<PessoaDTO> AddPessoa(@RequestBody PessoaFORM FORM, UriComponentsBuilder uriBuilder) {
        Pessoa pessoa = FORM.toForm(PessoaR);
        PessoaR.save(pessoa);
        URI uri = uriBuilder.path("/loja/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(new PessoaDTO(pessoa));
    }

    // ______________METODOS PRODUTOS______________
    @RequestMapping(value = "/loja/produto", method = RequestMethod.GET)
    public ResponseEntity<List<ProdutoDTO>> findAllProdutos() {
        List<Produto> findList = ProdutoR.findAll();
        ProdutoDTO DTO = new ProdutoDTO();
        return ResponseEntity.ok().body(DTO.EntidDTO(findList));
    }

    @RequestMapping(value = "/loja/produto", method = RequestMethod.POST)
    public ResponseEntity<ProdutoDTO> addProduto(@RequestBody ProdutoFORM FORM, UriComponentsBuilder uriBuilder) {
        Produto produto = FORM.toForm(ProdutoR);
        ProdutoR.save(produto);
        URI uri = uriBuilder.path("/loja/produto/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDTO(produto));
    }

    // ______________METODOS ENDERECOS______________
    // @RequestMapping(value = "/loja/endereco", method = RequestMethod.GET)
    // public List<Endereco> FindAllEnderecos() {
    //     return EnderecoR.findAll();
    // }

    // @RequestMapping(value = "/loja/endereco", method = RequestMethod.POST)
    // public Endereco AddEndereco(@RequestBody Endereco E) {
    //     return EnderecoR.save(E);
    // }

    // ______________METODOS PEDIDOS______________
    @RequestMapping(value = "/loja/pedido", method = RequestMethod.GET)
    public ResponseEntity<List<PedidoDTO>> FindAllPedidos() {
        List<Pedido> findList = PedidoR.findAll();
        PedidoDTO DTO = new PedidoDTO();
        return ResponseEntity.ok().body(DTO.EntidDTO(findList));
    } 

    @RequestMapping(value = "/loja/pedido", method = RequestMethod.POST)
    public ResponseEntity<PedidoDTO> AddPedido(@RequestBody PedidoFORM FORM, UriComponentsBuilder uriBuilder) {
        Pedido pedido = FORM.toForm(PedidoR,PessoaR,ProdutoR);
        URI uri = uriBuilder.path("/loja/pedido/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(new PedidoDTO(pedido));
    }

}