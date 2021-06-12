package com.projeto.loja.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.projeto.loja.models.Endereco;
import com.projeto.loja.models.dto.EnderecoDTO;
import com.projeto.loja.repositories.EnderecoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository EnderecoR;

    @GetMapping
    public ResponseEntity<?> FindAllEndereco() {
        List<Endereco> endereco = EnderecoR.findAll();
        EnderecoDTO DTO = new EnderecoDTO();
        return ResponseEntity.ok().body(DTO.EntidDTO(endereco));
    }

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