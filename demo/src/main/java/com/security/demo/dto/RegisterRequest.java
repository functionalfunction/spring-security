package com.security.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kullanıcı kayıt işlemi için gereken JSON objesi (username, email, password).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;  // Kullanıcı adı
    private String password;  // Şifre
    private String email;     // E-posta
}