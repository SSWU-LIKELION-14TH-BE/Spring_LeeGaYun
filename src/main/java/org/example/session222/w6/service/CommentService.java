package org.example.session222.w6.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.session222.w6.dto.CommentDTO;
import org.example.session222.w6.entity.Comment;
import org.example.session222.w6.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Optional<Comment> getComment(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public List<Comment> getCommentsByBoardId(Long boardId) {
        return commentRepository.findByBoardId(boardId);
    }

    public void postComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional
    public void putComment(CommentDTO commentDTO) {
        Comment comment = Comment.builder()
                .commentId(commentDTO.getCommentId())
                .boardId(commentDTO.getBoardId())
                .content(commentDTO.getContent())
                .writer(commentDTO.getWriter())
                .commentDate(LocalDate.now())
                .build();

        commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
