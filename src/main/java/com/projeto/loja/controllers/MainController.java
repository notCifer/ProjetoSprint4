package com.projeto.loja.controllers;

import java.net.URI;
import java.util.List;
import com.projeto.loja.models.Pedido;
import com.projeto.loja.models.Pessoa;
import com.projeto.loja.models.Produto;
import com.projeto.loja.models.dto.PedidoDTO;
import com.projeto.loja.models.dto.PessoaDTO;
import com.projeto.loja.models.form.PedidoFORM;
import com.projeto.loja.models.form.PessoaFORM;
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
    public List<PessoaDTO> FindAllPessoas() {
        List<Pessoa> findAll = PessoaR.findAll();
        PessoaDTO DTO = new PessoaDTO();
        return DTO.EntidDTO(findAll);
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
    public List<Produto> findAllProdutos() {
        return ProdutoR.findAll();
    }

    @RequestMapping(value = "/loja/produto", method = RequestMethod.POST)
    public Produto addProduto(@RequestBody Produto P) {
        return ProdutoR.save(P);
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
    public List<PedidoDTO> FindAllPedidos() {
        List<Pedido> findAll = PedidoR.findAll();
        PedidoDTO DTO = new PedidoDTO();
        return DTO.EntidDTO(findAll);
    }

    @RequestMapping(value = "/loja/pedido", method = RequestMethod.POST)
    public ResponseEntity<PedidoDTO> AddPedido(@RequestBody PedidoFORM FORM, UriComponentsBuilder uriBuilder) {
        Pedido pedido = FORM.toForm(PedidoR);
        PedidoR.save(pedido);
        URI uri = uriBuilder.path("/loja/pedido/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(new PedidoDTO(pedido));
    }

}