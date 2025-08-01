package com.forohub.api.controller;

import com.forohub.api.domain.user.DataAuthentication;
import com.forohub.api.domain.user.User;
import com.forohub.api.infra.security.DataTokenJWT;
import com.forohub.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataAuthentication datos) {
        System.out.println(datos);
        var token = new UsernamePasswordAuthenticationToken(datos.username(), datos.password());
        System.out.println(token);
        var auth = authenticationManager.authenticate(token);

        var tokenJWT = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }
}
