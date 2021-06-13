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
    @Size(min = 1,max = 1)
    private String sexo;
    @NotNull @NotEmpty
    private List<Endereco> endereco;

    public Pessoa toForm(PessoaRepository PessoaR) {
        char s = sexo.charAt(0);
        Pessoa pessoa= new Pessoa(nome, cpf, salario, s, endereco);
        PessoaR.save(pessoa);
        return pessoa;
    }

    public Pessoa Alter(Long id, PessoaRepository PessoaR){
        Pessoa pessoa = PessoaR.getById(id);
        return pessoa;
    }

}