package com.projeto.loja.models.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.projeto.loja.models.Pessoa;

public class PessoaDTO {

    private String nome;
    private Long cpf;
    private char sexo;

    public PessoaDTO() {
    }

    public PessoaDTO(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.cpf = pessoa.getCpf();
        this.sexo = pessoa.getSexo();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public PessoaDTO EntidDTO(Pessoa pessoa) {
        PessoaDTO DTO = new PessoaDTO();
        DTO.setNome(pessoa.getNome());
        DTO.setCpf(pessoa.getCpf());
        DTO.setSexo(pessoa.getSexo());
        return DTO;
    }

    public List<PessoaDTO> EntidDTO(List<Pessoa> pessoas) {
        return pessoas.stream().map(pessoa -> EntidDTO(pessoa)).collect(Collectors.toList());
    }

}