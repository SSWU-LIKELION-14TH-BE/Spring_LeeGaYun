package org.example.session222.w6.controller;

import lombok.RequiredArgsConstructor;
import org.example.session222.w6.dto.CommentDTO;
import org.example.session222.w6.entity.Comment;
import org.example.session222.w6.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/getComment")
    public Optional<Comment> getComment(@RequestParam(name = "commentId") Long commentId) {
        return commentService.getComment(commentId);
    }

    @GetMapping("/getComments")
    public List<Comment> getCommentsByBoardId(@RequestParam(name = "boardId") Long boardId) {
        return commentService.getCommentsByBoardId(boardId);
    }

    @PostMapping("/postComment")
    public void postComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = Comment.builder()
                .boardId(commentDTO.getBoardId())
                .content(commentDTO.getContent())
                .writer(commentDTO.getWriter())
                .build();

        commentService.postComment(comment);
    }

    @PutMapping("/putComment")
    public void putComment(@RequestBody CommentDTO commentDTO) {
        commentService.putComment(commentDTO);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public void deleteComment(@PathVariable(name = "commentId") Long commentId) {
        commentService.deleteComment(commentId);
    }
}
