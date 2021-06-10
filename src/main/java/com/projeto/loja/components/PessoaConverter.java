package com.projeto.loja.components;

import java.util.List;
import java.util.stream.Collectors;

import com.projeto.loja.models.Pessoa;
import com.projeto.loja.models.dto.PessoaDTO;

import org.springframework.stereotype.Component;

@Component
public class PessoaConverter {

    public PessoaDTO EntidDTO(Pessoa pessoa) {
        PessoaDTO DTO = new PessoaDTO();
        DTO.setNome(pessoa.getNome());
        DTO.setCpf(pessoa.getCpf());
        DTO.setSexo(pessoa.getSexo());
        return DTO;
    }

    public List<PessoaDTO> EntidDTO(List<Pessoa> pessoas) {
        return pessoas.stream().map(x -> EntidDTO(x)).collect(Collectors.toList());
    }

}