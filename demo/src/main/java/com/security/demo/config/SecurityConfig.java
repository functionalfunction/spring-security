package com.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Güvenlik yapılandırmaları
 */
@Configuration
public class SecurityConfig {

    /**
     * BCryptPasswordEncoder bean'ini Spring konteynerine ekler.
     *
     * @return BCryptPasswordEncoder instance'ı
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCryptPasswordEncoder'ı bean olarak döndür
    }
}