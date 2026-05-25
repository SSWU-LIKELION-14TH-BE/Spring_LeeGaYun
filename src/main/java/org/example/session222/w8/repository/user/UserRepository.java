package org.example.session222.w8.repository.user;

import org.example.session222.w8.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 특정 사용자 email로 사용자를 조회하는 메서드
    Optional<User> findByEmail(String email);
}
