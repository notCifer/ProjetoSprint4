package com.projeto.loja.models.dto;

import java.util.List;
import java.util.stream.Collectors;
import com.projeto.loja.models.Usuario;

public class UsuarioDTO {

    private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public UsuarioDTO EntidDTO(Usuario login) {
        UsuarioDTO DTO = new UsuarioDTO();
        DTO.setEmail(login.getEmail());
        return DTO;
    }

    public List<UsuarioDTO> EntidDTO(List<Usuario> logins) {
        return logins.stream().map(login -> EntidDTO(login)).collect(Collectors.toList());
    }

}