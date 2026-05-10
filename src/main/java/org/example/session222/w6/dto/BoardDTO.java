package org.example.session222.w6.dto;// dto

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BoardDTO {
    private Long boardId;
    private String title;
    private String content;
    private String writer;
    private Date postDate;
}