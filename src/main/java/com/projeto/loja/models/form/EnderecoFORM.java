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

    public Endereco toForm(EnderecoRepository EnderecoaR) {
        Endereco endereco= new Endereco(pais, estado, cidade, cep, rua);
        EnderecoaR.save(endereco);
        return endereco;
    }

}