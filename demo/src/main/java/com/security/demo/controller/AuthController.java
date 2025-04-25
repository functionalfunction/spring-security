package com.security.demo.controller;

import com.security.demo.dto.AuthenticationRequest;
import com.security.demo.dto.AuthenticationResponse;
import com.security.demo.dto.RegisterRequest;
import com.security.demo.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kullanıcı giriş ve kayıt işlemleri için uç noktalar.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Kullanıcı kaydı
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        String token = authenticationService.register(registerRequest);
        return ResponseEntity.ok(token); // JWT token'ı döner
    }

    /**
     * Kullanıcı giriş
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok(response);
    }
}