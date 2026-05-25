package org.example.session222.w8.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users") // user는 예약어라 보통 users 또는 member
@Getter // 값 읽기
@AllArgsConstructor // 빌더 사용을 위함
@Builder // 생성자 대신 가독성 향상
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 기본 생성자
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 아이디 (Primary Key)

    @Column(name = "email", length = 50, nullable = false,
            unique = true)
    private String email;
    // 로그인 시 아이디로 사용되는 이메일
    @Column(name = "password", length = 255, nullable = false)
    private String password;
    // 비밀번호
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    // 이름
    @Column(name = "profile_image", length = 1000)
    private String profileImage; // 프로필 사진
}