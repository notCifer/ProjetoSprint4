package com.projeto.loja.controllers;

import java.net.URI;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import com.projeto.loja.models.Produto;
import com.projeto.loja.models.dto.ProdutoDTO;
import com.projeto.loja.models.form.ProdutoFORM;
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

@RestController
@RequestMapping(value = "/protected/produto")
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
    public ResponseEntity<?> FindOneProduto(@PathVariable Long id) {
        try {
            Produto produto = ProdutoR.getById(id);
            ProdutoDTO DTO = new ProdutoDTO();
            return ResponseEntity.ok().body(DTO.EntidDTO(produto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto de ID: " + id + ", não existe!");
        }

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> RemoveProduto(@PathVariable Long id) {

        Produto produto = ProdutoR.getById(id);
        if (ProdutoR.findById(id).isPresent()) {
            ProdutoR.delete(produto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Produto de ID: " + id + " deletado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto de ID: " + id + ", não existe!");

    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> addProduto(@RequestBody @Valid ProdutoFORM FORM,
        UriComponentsBuilder uriBuilder) {
        Produto produto = FORM.toForm(ProdutoR);
        URI uri = uriBuilder.path("/protected/produto/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDTO().EntidDTO(produto));
    }
}