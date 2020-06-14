package com.ominext.auth.controller;

import com.ominext.auth.payload.request.LoginRequest;
import com.ominext.auth.payload.response.JwtResponse;
import com.ominext.common.security.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class CredentialsController {

    private final AuthenticationManager authManager;
    private final JwtConfig jwtConfig;

    public CredentialsController(AuthenticationManager authManager, JwtConfig jwtConfig) {
        this.authManager = authManager;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/auth/login")
    public JwtResponse login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword(), Collections.emptyList());

        Authentication authentication = authManager.authenticate(authToken);

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        long now = System.currentTimeMillis();
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", roles)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();

        JwtResponse response = new JwtResponse();
        response.setStatus(HttpStatus.SC_OK);
        response.setToken(jwtConfig.getPrefix() + token);
        return response;
    }
}
