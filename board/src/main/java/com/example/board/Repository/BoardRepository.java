package com.example.board.Repository;

import com.example.board.Entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    //제목을 통해 게시물 찾기
    Page<Board> findByTitleContaining(String serchKeyword, Pageable pageable);

}
