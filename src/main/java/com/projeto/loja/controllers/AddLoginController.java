package com.projeto.loja.controllers;

import java.net.URI;
import javax.validation.Valid;
import com.projeto.loja.models.Usuario;
import com.projeto.loja.models.dto.UsuarioDTO;
import com.projeto.loja.models.form.UsuarioFORM;
import com.projeto.loja.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/add")
@RestController
public class AddLoginController {

    @Autowired
    private UsuarioRepository LoginR;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid UsuarioFORM FORM, UriComponentsBuilder uriBuilder){
        Usuario login = FORM.toForm(LoginR);
        URI uri = uriBuilder.path("/add/{id}").buildAndExpand(login.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO().EntidDTO(login));
    }
}