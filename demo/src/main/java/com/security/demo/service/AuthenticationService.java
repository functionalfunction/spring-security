package com.security.demo.service;

import com.security.demo.dto.AuthenticationRequest;
import com.security.demo.dto.AuthenticationResponse;
import com.security.demo.dto.RegisterRequest;
import com.security.demo.entity.User;
import com.security.demo.repository.NoteRepository;
import com.security.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Kullanıcı kayıt ve giriş işlemlerini yöneten servis.
 */
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Yeni bir kullanıcı kaydeder
     */
    @Transactional
    public String register(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Şifreyi hash'liyoruz

        userRepository.save(user);  // Kullanıcıyı veritabanına kaydediyoruz

        // Kullanıcı kaydederken, JWT token'ı döndürüyoruz
        return jwtService.generateToken(user.getUsername());
    }

    /**
     * Kullanıcıyı giriş yapmasını sağlar (username ve password kontrolü yapar)
     */
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        // Kullanıcı adıyla veritabanından kullanıcıyı bul
        User user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Şifreyi doğrula
        if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // JWT token'ı oluştur ve geri döndür
        String token = jwtService.generateToken(user.getUsername());
        return new AuthenticationResponse(token);
    }
}