package com.projeto.loja.models.form;

import javax.validation.constraints.NotBlank;
import com.projeto.loja.models.Endereco;
import com.projeto.loja.repositories.EnderecoRepository;

public class EnderecoFORM {

    @NotBlank
    private String pais;
    @NotBlank
    private String estado;
    @NotBlank
    private String cidade;
    @NotBlank
    private String cep;
    @NotBlank
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
        Endereco endereco= new Endereco(pais, estado, cidade, cep, rua);
        EnderecoaR.save(endereco);
        return endereco;
    }

}