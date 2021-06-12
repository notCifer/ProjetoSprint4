package com.projeto.loja.controllers;

import javax.validation.Valid;
import com.projeto.loja.models.dto.TokenDTO;
import com.projeto.loja.models.form.LoginFORM;
import com.projeto.loja.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private TokenService TokenS;

    @Autowired
    private AuthenticationManager AuthenticationM;

    @PostMapping
    public ResponseEntity<TokenDTO> Authentic(@RequestBody @Valid LoginFORM FORM) {
        UsernamePasswordAuthenticationToken Login = FORM.converter();
        try {
            Authentication auth = AuthenticationM.authenticate(Login);
            String Token = TokenS.gerarToken(auth);
            return ResponseEntity.ok(new TokenDTO(Token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}