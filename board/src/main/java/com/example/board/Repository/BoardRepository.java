package com.example.board.Repository;

import com.example.board.Entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    //������ ���� �Խù� ã��
    Page<Board> findByTitleContaining(String serchKeyword, Pageable pageable);

}
