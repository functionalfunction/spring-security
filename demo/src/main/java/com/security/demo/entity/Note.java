package com.security.demo.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Kullanıcılara ait notları temsil eden JPA Entity sınıfı.
 */
@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Otomatik artan ID
    private Long id;

    @Column(nullable = false)
    private String title; // Not başlığı

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // Notun içeriği

    @Column(nullable = false)
    private LocalDateTime createdAt; // Oluşturulma tarihi

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // Notu oluşturan kullanıcıya bağlanır
    private User user;
}