package com.projeto.loja.services;

import java.util.Optional;

import com.projeto.loja.models.Login;
import com.projeto.loja.repositories.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private LoginRepository LoginR;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Login> login = LoginR.findByemail(username);
        if (login.isPresent()) {
            return login.get();
        }
        throw new UsernameNotFoundException("Email ou Senha inváldios");

    }
    
}