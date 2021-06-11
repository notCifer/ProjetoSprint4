package com.projeto.loja.models.form;

import java.util.List;
import java.util.stream.Collectors;
import com.projeto.loja.models.Endereco;
import com.projeto.loja.models.Pessoa;
import com.projeto.loja.models.dto.PessoaDTO;
import com.projeto.loja.repositories.PessoaRepository;

public class PessoaFORM {

    private String nome;
    private Long cpf;
    private Double salario;
    private char sexo;
    private List<Endereco> endereco;
    
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

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    public Pessoa toForm(PessoaRepository PessoaR){
        return new Pessoa(nome, cpf, salario, sexo, endereco);
    }

}