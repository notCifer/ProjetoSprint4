package com.projeto.loja.controllers;

import java.net.URI;
import java.util.List;
import com.projeto.loja.models.Produto;
import com.projeto.loja.models.dto.ProdutoDTO;
import com.projeto.loja.models.form.ProdutoFORM;
import com.projeto.loja.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/loja/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository ProdutoR;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAllProdutos() {
        List<Produto> findList = ProdutoR.findAll();
        ProdutoDTO DTO = new ProdutoDTO();
        return ResponseEntity.ok().body(DTO.EntidDTO(findList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> FindOnePessoa(@PathVariable Long id){
        Produto produto = ProdutoR.getById(id);
        ProdutoDTO DTO = new ProdutoDTO();
        return ResponseEntity.ok().body(DTO.EntidDTO(produto));
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> addProduto(@RequestBody ProdutoFORM FORM, UriComponentsBuilder uriBuilder) {
        Produto produto = FORM.toForm(ProdutoR);
        ProdutoR.save(produto);
        URI uri = uriBuilder.path("/loja/produto/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDTO().EntidDTO(produto));
    }
}