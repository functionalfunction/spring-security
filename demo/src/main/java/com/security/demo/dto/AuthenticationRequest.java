package com.security.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kullanıcının giriş yaparken göndereceği JSON objesi (username ve password).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    private String username;  // Kullanıcı adı
    private String password;  // Şifre
}