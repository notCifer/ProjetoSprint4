package com.projeto.loja.models.form;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import com.projeto.loja.models.Login;
import com.projeto.loja.repositories.LoginRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginFORM {

    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    private String senha;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    public Login toForm(LoginRepository LoginR){
        Login login = new Login(email, senha);
        LoginR.save(login);
        return login;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email,senha);
    }
}