package org.example.session222.w6.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", unique = true, nullable = false)
    private Long commentId;

    @Column(name = "board_id", nullable = false)
    private Long boardId;

    @Column(length = 200, nullable = false)
    private String content;

    @Column(length = 15, nullable = false)
    private String writer;

    private LocalDate commentDate;

    @PrePersist
    protected void onCreate() {
        this.commentDate = LocalDate.now();
    }
}
