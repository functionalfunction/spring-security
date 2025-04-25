package com.security.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Kullanıcı login olduktan sonra alacağı JWT token'ı.
 */
@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;  // JWT token
}