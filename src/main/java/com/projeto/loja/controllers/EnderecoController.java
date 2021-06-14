package com.projeto.loja.controllers;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import com.projeto.loja.models.Endereco;
import com.projeto.loja.models.dto.EnderecoDTO;
import com.projeto.loja.models.form.EnderecoFORM;
import com.projeto.loja.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/endereco")
@Api(description = "(PERMITIDO) Funções de endereços ", tags = { "Endereço" })
public class EnderecoController {

    @Autowired
    private EnderecoRepository EnderecoR;

    @ApiOperation(value = "Buscar todos os endereços.")
    @GetMapping
    public ResponseEntity<?> FindAllEndereco() {
        try {
            List<Endereco> endereco = EnderecoR.findAll();
            EnderecoDTO DTO = new EnderecoDTO();
            return ResponseEntity.ok().body(DTO.EntidDTO(endereco));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista está vazia");
        }
    }

    @ApiOperation(value = "Buscar um endereço pelo ID.")
    @GetMapping("/{id}")
    public ResponseEntity<?> FindOneEndereco(@PathVariable Long id) {
        try {
            Endereco endereco = EnderecoR.getById(id);
            EnderecoDTO DTO = new EnderecoDTO();
            return ResponseEntity.ok().body(DTO.EntidDTO(endereco));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O endereço de ID : " + id + ", não existe!");
        }

    }

    @ApiOperation(value = "Alterar um endereço pelo ID.")
    @PutMapping("/{id}")
    public ResponseEntity<?> AlterPessoa(@PathVariable Long id, @Valid EnderecoFORM FORM) {
        try {
            Endereco endereco = EnderecoR.getById(id);
            if (endereco != null) {
                endereco.setCep(FORM.getCep());
                endereco.setCidade(FORM.getCidade());
                endereco.setEstado(FORM.getEstado());
                endereco.setPais(FORM.getPais());
                endereco.setRua(FORM.getRua());
                EnderecoDTO DTO = new EnderecoDTO();
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(DTO.EntidDTO(endereco)); 
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    

    @ApiOperation(value = "Deletar um endereço pelo ID.")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> RemoveEndereco(@PathVariable Long id) {
        Endereco endereco = EnderecoR.getById(id);
        if (EnderecoR.findById(id).isPresent()) {
            EnderecoR.delete(endereco);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Endereco de ID: " + id + " deletado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O pedido de ID : " + id + ", não existe!");
    }

}