package com.projeto.loja.controllers;

import java.net.URI;
import java.util.List;
import com.projeto.loja.models.Pessoa;
import com.projeto.loja.models.dto.PessoaDTO;
import com.projeto.loja.models.form.PessoaFORM;
import com.projeto.loja.repositories.PessoaRepository;
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
@RequestMapping(value = "/loja/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository PessoaR;

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> FindAllPessoas() {
        List<Pessoa> findList = PessoaR.findAll();
        PessoaDTO DTO = new PessoaDTO();
        return ResponseEntity.ok().body(DTO.EntidDTO(findList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> FindOnePessoa(@PathVariable Long id){
        Pessoa pessoa = PessoaR.getById(id);
        PessoaDTO DTO = new PessoaDTO();
        return ResponseEntity.ok().body(DTO.EntidDTO(pessoa));
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> AddPessoa(@RequestBody PessoaFORM FORM, UriComponentsBuilder uriBuilder) {
        Pessoa pessoa = FORM.toForm(PessoaR);
        PessoaR.save(pessoa);
        URI uri = uriBuilder.path("/loja/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(new PessoaDTO().EntidDTO(pessoa));
    }

}