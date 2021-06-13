package com.projeto.loja.controllers;

import java.net.URI;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import com.projeto.loja.models.Pessoa;
import com.projeto.loja.models.dto.PessoaDTO;
import com.projeto.loja.models.form.PessoaFORM;
import com.projeto.loja.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

@RestController
@RequestMapping(value = "/pessoa")
@Api(description = "(PERMITIDO) Funções de pessoas", tags = { "Pessoa" })
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
    public ResponseEntity<?> FindOnePessoa(@PathVariable Long id) {
        try {
            Pessoa pessoa = PessoaR.getById(id);
            PessoaDTO DTO = new PessoaDTO();
            return ResponseEntity.ok().body(DTO.EntidDTO(pessoa));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa de ID: " + id + ", não existe!");
        }
    }

    // @PutMapping("/{id}")
    // public ResponseEntity AlterPessoa(@PathVariable Long id, @Valid PessoaFORM
    // FORM) {
    // try {
    // Pessoa pessoa = FORM.Alter(id, PessoaR);
    // PessoaDTO DTO = new PessoaDTO();
    // return ResponseEntity.status(HttpStatus.ACCEPTED).body(DTO.EntidDTO(pessoa));
    // } catch (DataIntegrityViolationException SQL) {
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O numero de CPF ja
    // foi cadastro em outra pessoa");
    // }

    // }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> RemovePessoa(@PathVariable Long id) {

        Pessoa pessoa = PessoaR.getById(id);
        if (PessoaR.findById(id).isPresent()) {
            PessoaR.delete(pessoa);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Pessoa de ID: " + id + " deletada com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa de ID: " + id + ", não existe!");

    }

    @PostMapping
    public ResponseEntity<?> AddPessoa(@RequestBody @Valid PessoaFORM FORM, UriComponentsBuilder uriBuilder) {
        try {
            Pessoa pessoa = FORM.toForm(PessoaR);
            URI uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
            return ResponseEntity.created(uri).body(new PessoaDTO().EntidDTO(pessoa));
        } catch (DataIntegrityViolationException SQL) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("O numero de CPF ja foi cadastro em outra pessoa");
        }

    }

}