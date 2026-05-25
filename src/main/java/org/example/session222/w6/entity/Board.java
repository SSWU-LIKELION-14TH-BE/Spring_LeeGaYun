package org.example.session222.w6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", unique = true, nullable = false)
    private Long boardId;

    @Column(length = 15, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String content;

    @Column(length = 15, nullable = false)
    private String writer;

    // 날짜만 표시, 시간 X
    private LocalDate postDate;

    @Column
    private String image;

    @PrePersist
// ipa의 콜백 메서드, 엔티티가 처음 저장되기 직전에 실행 즉, 새로운 row 생성
    protected void onCreate() { this.postDate = LocalDate.now(); }
}
