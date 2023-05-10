package com.example.demo.handlers.security;

import com.example.demo.domain.user.AuthService;
import com.example.demo.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String JWTToken = retrieveToken(request);
        if(JWTToken != null){
            String tokenSubject = getTokenSubject(JWTToken);
            UserDetails user = userRepo.findByLogin(tokenSubject);
            var authentication =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String retrieveToken(HttpServletRequest request) {
        var JWTToken = request.getHeader("authorization");
        if(JWTToken != null){
            return JWTToken.replace("Bearer ", "");
        }
        return null;
    }

    private String getTokenSubject(String JWTToken) {
        return tokenService.decodeAuthToken(JWTToken);
    }
}
