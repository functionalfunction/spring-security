package com.security.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Kullanıcı bilgilerini temsil eden JPA Entity sınıfı.
 */
@Entity
@Table(name = "users") // Tablo adı "users" olarak verildi
@Data // Lombok: getter/setter/toString/hashCode/equals otomatik
@NoArgsConstructor // Boş constructor oluşturur
@AllArgsConstructor // Tüm alanları içeren constructor oluşturur
@Builder // Nesne oluştururken .builder() stilini sağlar
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Otomatik artan ID
    private Long id;

    @Column(unique = true, nullable = false) // Benzersiz ve boş olamaz
    private String username;

    @Column(nullable = false) // Şifre boş olamaz
    private String password;

    @Column(nullable = false) // email boş olamaz
    private String email;

    /**
     * Kullanıcının sahip olduğu roller (yetkiler).
     * Örnek: ROLE_USER, ROLE_ADMIN
     * ElementCollection: Basit tiplerin koleksiyonları için kullanılır.
     */
    @ElementCollection(fetch = FetchType.EAGER) // Roller her kullanıcıyla birlikte hemen getirilir
    @CollectionTable(
            name = "user_roles", // Roller ayrı bir tabloya yazılır
            joinColumns = @JoinColumn(name = "user_id") // Anahtarla user tablosuna bağlanır
    )
    @Column(name = "role") // Kolon adı "role" olacak
    private Set<String> roles;
}