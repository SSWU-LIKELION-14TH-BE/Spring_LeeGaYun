package org.example.session222.w6.repository;

import org.example.session222.w6.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByBoardId(Long boardId); //boardId를 가지고 찾겠다

    void deleteByBoardId(Long boardId);
}
