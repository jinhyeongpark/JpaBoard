package com.example.board.Service;

import com.example.board.Entity.Board;
import com.example.board.Repository.BoardRepository;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    //�� �ۼ� ó��
    public void write(Board board, MultipartFile file) throws IOException {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files"; //���� ��� ����

        UUID uuid = UUID.randomUUID(); //���� �̸� ���� ����

        String fileName = uuid + "_" + file.getOriginalFilename(); //���� �̸��� ������ ���� �̸� �տ��ٰ� ����

        File saveFile = new File(projectPath, fileName); //������ ��ο� ���� ��ü ����

        file.transferTo(saveFile); //���ε�� ������ ��ü�� ����

        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);

        boardRepository.save(board);
    }
    //�Խñ� ����Ʈ ó��
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }
    //Ư�� �Խñ� �ҷ�����
    public Board boardView(Integer id) {
        return boardRepository.findById(id).get();
    }
    //Ư�� �Խñ� ����
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }

}
