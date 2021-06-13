package com.projeto.loja.models.form;

import com.projeto.loja.models.Usuario;
import com.projeto.loja.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioFORM {

    private String nome;
    private String email;
	private String senha;

	public void setSenha(String senha) {
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

    public Usuario toForm(UsuarioRepository UsuarioR){
        Usuario usuario = new Usuario(nome, email, senha);
        UsuarioR.save(usuario);
        return usuario;
    }

}