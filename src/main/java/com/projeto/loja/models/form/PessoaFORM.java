package com.projeto.loja.models.form;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.projeto.loja.models.Endereco;
import com.projeto.loja.models.Pessoa;
import com.projeto.loja.repositories.PessoaRepository;

public class PessoaFORM {

    @NotBlank
    private String nome;
    @NotNull
    private Long cpf;
    @NotNull
    private Double salario;
    @NotBlank
    @Size(min = 1, max = 1)
    private String sexo;
    @NotNull
    @NotEmpty
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
        return sexo.charAt(0);
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    public Pessoa toForm(PessoaRepository PessoaR) {
        Pessoa pessoa = new Pessoa(nome, cpf, salario, getSexo() , endereco);
        PessoaR.save(pessoa);
        return pessoa;
    }
}