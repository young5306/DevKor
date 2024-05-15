package com.practice.loginPractice.controller;

import com.practice.loginPractice.dto.BoardRequest;
import com.practice.loginPractice.dto.BoardResponse;
import com.practice.loginPractice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boards/create")
    public String createBoard(BoardRequest req){
        System.out.println(req.toString());
        boardService.createBoard(req);
        return "redirect:/form";
    }

    @GetMapping("/boards/content")
    public String getBoards(Model model){
        List<BoardResponse> resList = boardService.getBoards();
        model.addAttribute("resList", resList);
        return "content"; // redirect하면 데이터 사라짐
    }

    // api를 사용한 통신의 경우엔 @DeleteMapping을 통한 삭제가 가능하지만
    // form 테그나 uri로 직접 접근하는 경우엔 @DeleteMapping 사용이 불가
    @GetMapping("boards/delete")
    public String deleteBoard(Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boards/content";
    }

}
