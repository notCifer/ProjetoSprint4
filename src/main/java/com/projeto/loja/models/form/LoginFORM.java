package com.projeto.loja.models.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginFORM {

	private String email;
	private String senha;

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}

}
