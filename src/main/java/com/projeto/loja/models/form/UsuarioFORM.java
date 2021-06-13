package com.projeto.loja.models.form;

import javax.validation.constraints.NotBlank;

import com.projeto.loja.models.Usuario;
import com.projeto.loja.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioFORM {

    @NotBlank
    private String email;
    @NotBlank
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    public Usuario toForm(UsuarioRepository UsuarioR) {
        Usuario usuario = new Usuario(email, senha);
        UsuarioR.save(usuario);
        return usuario;
    }

}