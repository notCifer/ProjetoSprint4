package com.projeto.loja.controllers;

import java.net.URI;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import com.projeto.loja.models.Pedido;
import com.projeto.loja.models.dto.PedidoDTO;
import com.projeto.loja.models.form.PedidoFORM;
import com.projeto.loja.repositories.PedidoRepository;
import com.projeto.loja.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/protected/pedido")
@Api(description = "(AUTENTICADO) Funções de pedidos ", tags = { "Pedido" })
public class PedidoController {

    @Autowired
    private PedidoRepository PedidoR;

    @Autowired
    private ProdutoRepository ProdutoR;

    @ApiOperation(value = "Buscar todos os pedidos.")
    @GetMapping
    public ResponseEntity<?> FindAllPedidos() {
        try {
            List<Pedido> pedidos = PedidoR.findAll();
            PedidoDTO DTO = new PedidoDTO();
            return new ResponseEntity<>(DTO.EntidDTO(pedidos), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista está vazia");
        }
    }

    @ApiOperation(value = "Buscar um pedido pelo ID.")
    @GetMapping("/{id}")
    public ResponseEntity<?> FindOnePedido(@PathVariable Long id) {
        try {
            Pedido pedido = PedidoR.getById(id);
            PedidoDTO DTO = new PedidoDTO();
            return ResponseEntity.ok().body(DTO.EntidDTO(pedido));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O pedido de ID : " + id + ", não existe!");
        }

    }

    @ApiOperation(value = "Deletar um pedido pelo ID.")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> RemovePedido(@PathVariable Long id) {

        Pedido pedido = PedidoR.getById(id);
        if (PedidoR.findById(id).isPresent()) {
            PedidoR.delete(pedido);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Pedido de ID: " + id + " deletado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O pedido de ID : " + id + ", não existe!");

    }

    @ApiOperation(value = "Criar um pedido")
    @PostMapping
    public ResponseEntity<PedidoDTO> AddPedido(@RequestBody @Valid PedidoFORM FORM, UriComponentsBuilder uriBuilder) {
        Pedido pedido = FORM.toForm(PedidoR, ProdutoR);
        URI uri = uriBuilder.path("/protected/pedido/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(new PedidoDTO().EntidDTO(pedido));
    }

}