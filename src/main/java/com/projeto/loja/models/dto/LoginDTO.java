package com.projeto.loja.models.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.projeto.loja.models.Usuario;

public class LoginDTO {

    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public LoginDTO EntidDTO(Usuario login) {
        LoginDTO DTO = new LoginDTO();
        DTO.setEmail(login.getEmail());
        return DTO;
    }

    public List<LoginDTO> EntidDTO(List<Usuario> logins) {
        return logins.stream().map(login -> EntidDTO(login)).collect(Collectors.toList());
    }

}