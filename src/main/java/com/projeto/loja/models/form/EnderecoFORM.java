package com.projeto.loja.models.form;

import com.projeto.loja.models.Endereco;
import com.projeto.loja.repositories.EnderecoRepository;

public class EnderecoFORM {

    private String pais;
    private String estado;
    private String cidade;
    private String cep;
    private String rua;

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Endereco toForm(EnderecoRepository EnderecoaR) {
        return new Endereco(pais, estado, cidade, cep, rua);
    }

}