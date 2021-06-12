package com.projeto.loja.controllers;

import java.net.URI;
import java.util.List;
import com.projeto.loja.models.Pedido;
import com.projeto.loja.models.dto.PedidoDTO;
import com.projeto.loja.models.form.PedidoFORM;
import com.projeto.loja.repositories.PedidoRepository;
import com.projeto.loja.repositories.PessoaRepository;
import com.projeto.loja.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/loja/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository PedidoR;

    @Autowired
    private PessoaRepository PessoaR;

    @Autowired
    private ProdutoRepository ProdutoR;


    @GetMapping
    public ResponseEntity<List<PedidoDTO>> todos() {
        List<Pedido> pedidos = PedidoR.findAll();
        PedidoDTO DTO = new PedidoDTO();
        return new ResponseEntity<>(DTO.EntidDTO(pedidos), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> FindAllPedidos(@PathVariable Long id) {
        Pedido pedido = PedidoR.getById(id);
        PedidoDTO DTO = new PedidoDTO();
        return ResponseEntity.ok().body(DTO.EntidDTO(pedido));
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> AddPedido(@RequestBody PedidoFORM FORM, UriComponentsBuilder uriBuilder) {
        Pedido pedido = FORM.toForm(PedidoR, PessoaR, ProdutoR);
        URI uri = uriBuilder.path("/loja/pedido/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(new PedidoDTO().EntidDTO(pedido));
    }

}