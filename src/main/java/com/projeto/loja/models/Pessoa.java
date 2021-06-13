package com.projeto.loja.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private Long cpf;
    private Double salario;
    private char sexo;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    private List<Endereco> endereco;

    public Pessoa() {
    }

    public Pessoa(String nome, Long cpf, Double salario, char sexo, List<Endereco> endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.sexo = sexo;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public Double getSalario() {
        return salario;
    }

    public char getSexo() {
        return sexo;
    }

    public List<Endereco> getEndereços() {
        return endereco;
    }
}