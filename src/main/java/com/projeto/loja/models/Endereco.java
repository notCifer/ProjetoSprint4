package com.projeto.loja.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pais;
    private String estado;
    private String cidade;
    private String cep;
    private String rua;

    public Endereco() {
    }

    public Endereco(String pais, String estado, String cidade, String cep, String rua) {
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.rua = rua;
    }

    public Long getId() {
        return id;
    }

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

}
