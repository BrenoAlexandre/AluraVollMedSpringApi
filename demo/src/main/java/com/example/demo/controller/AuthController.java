package com.example.demo.controller;

import com.example.demo.domain.requests.*;
import com.example.demo.domain.responses.*;
import com.example.demo.domain.user.*;
import com.example.demo.handlers.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginData data){
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = manager.authenticate(token);

        String JWTToken = tokenService.createAuthToken((User) auth.getPrincipal());

        return ResponseEntity.ok().header("authorization", JWTToken).build();
    }

    @PostMapping("/new")
    public ResponseEntity createUser(@RequestBody @Valid SignupData data, UriComponentsBuilder uriBuilder) throws Exception {
        var user = authService.registerNewUser(data);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDetailsResponse(user));
    }
}
