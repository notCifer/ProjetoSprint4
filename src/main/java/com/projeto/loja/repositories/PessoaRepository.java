package com.projeto.loja.repositories;

import com.projeto.loja.models.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository  extends JpaRepository<Pessoa,Long>{
    
}