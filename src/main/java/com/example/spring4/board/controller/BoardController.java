package com.example.spring4.board.controller;

import com.example.spring4.board.service.BoardService;
import com.example.spring4.board.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller //싱글톤객체생성 + 아래에 나온 주소와 함수를 스프링에 등록
@RequestMapping("board") //contextpath/board
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("board") //contextpath/board/board
    public String board(Model model) {
        System.out.println("board 화면 요청>>>>>>>>>>>>>>>> ");
        //전체 목록
        List<BoardVO> list  = boardService.selectBoardAll();
        System.out.println("list.size() " + list.size());
        System.out.println(" -------------list----------");
        System.out.println(list);
        model.addAttribute("list", list);
        //model은 templates파일까지 list데이터 전달(주소가 전달)
        return "board/board"; //templates/board/board.html을 호출..
    }

    @GetMapping("create")
    public String create() {
        return "board/create"; //글쓰기 화면 요청
    }

    @PostMapping("create2")
    public String create2(BoardVO boardVO) {
        boardService.insertBoard(boardVO);
        //db에 넣고!! --> 삽입성공! 또는 게시판리스트!
        //list를 구해서 넘겨야하는데 전달될 list가 없어서 빈화면이 나타남.!
        //return "board/board"; //templates/board/board.html호출!
        return "redirect:/board/board";
        //response.sendRedirect("/board/board")를 브라우저에게 호출하도록 명령
        //get요청!
        //@GetMapping("board")로 넘어감.
    }
    @GetMapping("read")
    public String read(int no, Model model) {
        //검색해서 가지고 온다음에
        BoardVO boardVO = boardService.selectBoardByNo(no);

        //model로 넘기자.
        model.addAttribute("boardVO", boardVO);
        return "board/read";
    }
}
