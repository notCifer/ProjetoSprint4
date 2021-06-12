package com.projeto.loja.tools;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.projeto.loja.services.TokenService;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter extends OncePerRequestFilter {

    private TokenService TokenS;

    public AuthTokenFilter(TokenService tokenS) {
        TokenS = tokenS;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = RecuperarToken(request);
        boolean Check = TokenS.isTokenValido(token);
        if (Check) {
            // Autheticate();
        }
        filterChain.doFilter(request, response);
    }

    // private void Autheticate() {
    //     UsernamePasswordAuthenticationToken aToken = new UsernamePasswordAuthenticationToken();
    //     SecurityContextHolder.getContext().setAuthentication(aToken);
    // }

    private String RecuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }

}