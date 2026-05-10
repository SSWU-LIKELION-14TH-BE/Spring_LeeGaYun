package org.example.session222.w6.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    private Long commentId;
    private Long boardId;
    private String content;
    private String writer;
    private LocalDate commentDate;
}
