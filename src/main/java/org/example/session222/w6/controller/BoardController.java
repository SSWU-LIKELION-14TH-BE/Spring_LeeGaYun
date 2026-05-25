package org.example.session222.w6.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.session222.w6.dto.BoardDTO;
import org.example.session222.w6.entity.Board;
import org.example.session222.w6.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/api/board") // 나머지 굳이 적지 않아도 선언이 됨
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시글 하나 띄우기
    @GetMapping("/getBoard")
    public Optional<Board> getBoard(@RequestParam(name = "boardId") Long boardId) {
        return boardService.getBoard(boardId);
    }

    // 게시글 작성하기
    @PostMapping("/postBoard")
    public void postBoard(@RequestBody BoardDTO boarddto) {
        Board board = Board.builder()
                .title(boarddto.getTitle())
                .content(boarddto.getContent())
                .writer(boarddto.getWriter())
                .build();
        boardService.postBoard(board);
    }

    // 게시글 수정하기
    @PutMapping("/putBoard")
    public void putBoard(@RequestBody BoardDTO boarddto){
        boardService.putBoard(boarddto);
    }

    @DeleteMapping("/deleteBoard/{boardId}")
    public void deleteBoard(@PathVariable(name = "boardId") Long boardId) {
        boardService.deleteBoard(boardId);
    }

    // 이미지 포함 게시글 올리기
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute BoardDTO imageBoardDTO){
        try {
            BoardDTO request = BoardDTO.builder()
                    .title(imageBoardDTO.getTitle())
                    .content(imageBoardDTO.getContent())
                    .image(imageBoardDTO.getImage())
                    .writer(imageBoardDTO.getWriter())
                    .build();

            boardService.ImageBoard(request);

            return ResponseEntity.ok("파일 업로드 성공");
        } catch (Exception e) {
            log.error("파일 업로드 실패", e);
            return ResponseEntity.status(400).build();
        }
    }
}

