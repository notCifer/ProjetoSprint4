package com.projeto.loja.models.dto;

import java.util.List;
import java.util.stream.Collectors;
import com.projeto.loja.models.Endereco;

public class EnderecoDTO {

    private String cidade;
    private String rua;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public EnderecoDTO EntidDTO(Endereco endereco) {
        EnderecoDTO DTO = new EnderecoDTO();
        DTO.setCidade(endereco.getCidade());
        DTO.setRua(endereco.getRua());
        return DTO;
    }

    public List<EnderecoDTO> EntidDTO(List<Endereco> enderecos) {
        return enderecos.stream().map(endereco -> EntidDTO(endereco)).collect(Collectors.toList());
    }

}