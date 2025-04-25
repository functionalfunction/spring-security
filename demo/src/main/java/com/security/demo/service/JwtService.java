package com.security.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * JWT token işlemleri için yardımcı servis.
 */
@Service
public class JwtService {

    @Value("${jwt.secret}")  // Secret key'i application.properties veya application.yml'den alıyoruz
    private String SECRET_KEY;

    // Token geçerlilik süresi (örnek: 1 saat)
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 saat (milisaniye cinsinden)

    /**
     * JWT token oluşturur.
     *
     * @param username Kullanıcı adı
     * @return Oluşturulan JWT token
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)  // Token içinde kullanıcı adı
                .setIssuedAt(new Date())  // Token'ın oluşturulma zamanı
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Token'ın son kullanma tarihi
                .signWith(getSignInKey())  // Secret key ile imzalama
                .compact();  // Token'ı oluştur ve döndür
    }

    /**
     * Token'ı doğrulamak ve claim'leri almak için kullanılır.
     *
     * @param token JWT token
     * @return Token içindeki claim'ler (veriler)
     */
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignInKey())  // Secret key ile doğrulama yap
                .build()
                .parseClaimsJws(token)
                .getBody();  // Token içindeki claim'leri al
    }

    /**
     * Token'ı doğrular ve geçerliliğini kontrol eder.
     *
     * @param token JWT token
     * @return Token geçerliyse true, geçersizse false
     */
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    /**
     * Token'dan kullanıcı adı alır.
     *
     * @param token JWT token
     * @return Kullanıcı adı
     */
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();  // Token'dan kullanıcı adı (subject) al
    }

    /**
     * Secret key'i döndürür.
     *
     * @return Secret key
     */
    private SecretKey getSignInKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);  // Secret key'i oluştur
    }
}