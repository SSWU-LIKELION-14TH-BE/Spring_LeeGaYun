package org.example.session222.w9.repository;

import org.example.session222.w9.entity.W9User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface W9UserRepository extends JpaRepository<W9User, Long> {
    Optional<W9User> findByUsername(String username);
}
