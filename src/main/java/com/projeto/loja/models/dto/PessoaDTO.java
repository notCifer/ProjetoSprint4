package com.projeto.loja.models.dto;

import java.util.List;
import java.util.stream.Collectors;
import com.projeto.loja.models.Pessoa;

public class PessoaDTO {

    private String nome;
    private Long cpf;
    private char sexo;
    private List<EnderecoDTO> enderecoDTO;

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

    public List<EnderecoDTO> getEnderecoDTO() {
        return enderecoDTO;
    }

    public void setEnderecoDTO(List<EnderecoDTO> enderecos) { 
        this.enderecoDTO = enderecos;
    }

    public PessoaDTO EntidDTO(Pessoa pessoa) {
        PessoaDTO DTO = new PessoaDTO();
        EnderecoDTO DTO2 = new EnderecoDTO();
        DTO.setNome(pessoa.getNome());
        DTO.setCpf(pessoa.getCpf());
        DTO.setSexo(pessoa.getSexo());
        DTO.setEnderecoDTO(DTO2.EntidDTO(pessoa.getEndereços()));
        return DTO;
    }

    public List<PessoaDTO> EntidDTO(List<Pessoa> pessoas) {
        return pessoas.stream().map(pessoa -> EntidDTO(pessoa)).collect(Collectors.toList());
    }

}