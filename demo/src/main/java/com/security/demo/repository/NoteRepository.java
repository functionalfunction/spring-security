package com.security.demo.repository;

import com.security.demo.entity.Note;
import com.security.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Not Entity için veri tabanı işlemlerini yöneten Repository arayüzü.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

    /**
     * Belirli bir kullanıcıya ait tüm notları getirir.
     */
    List<Note> findByUser(User user);
}