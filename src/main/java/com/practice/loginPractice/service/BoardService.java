package com.practice.loginPractice.service;

import com.practice.loginPractice.domain.Board;
import com.practice.loginPractice.dto.BoardRequest;
import com.practice.loginPractice.dto.BoardResponse;
import com.practice.loginPractice.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public BoardResponse createBoard(BoardRequest req) {
        Board board = boardRepository.save(
                Board.builder()
                        .title(req.getTitle())
                        .content(req.getContent())
                        .build()
        );
        return new BoardResponse(board);
    }

    public List<BoardResponse> getBoards(){
        List<Board> boardList = boardRepository.findAll();
        System.out.println(boardList); //
        List<BoardResponse> resList = new ArrayList<>();
        for(Board board : boardList){
            resList.add(new BoardResponse(board));
        }
       return resList;
    }

    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
