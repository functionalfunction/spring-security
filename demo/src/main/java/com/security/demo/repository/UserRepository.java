package com.security.demo.repository;

import com.security.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Kullanıcı veritabanı işlemlerini yöneten Repository arayüzü.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Kullanıcı adı ile kullanıcıyı bulur.
     */
    Optional<User> findByUsername(String username);
}